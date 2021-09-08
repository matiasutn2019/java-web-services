package ar.com.educacionit.ws.repository.impl;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.com.educacionit.ws.domain.User;
import ar.com.educacionit.ws.exceptions.GenericException;
import ar.com.educacionit.ws.repository.UserRepository;
import ar.com.educacionit.ws.repository.hibernate.HibernateBaseRepository;

public class UserRepositoryHibImpl extends HibernateBaseRepository implements UserRepository {

	public UserRepositoryHibImpl() {
		super();
	}
	
	@Override
	public User getUser(String username) throws GenericException {
		
		User user = null;
		
		Session session = super.factory.getCurrentSession();
		
		try {

			// Start Transaction.
			session.getTransaction().begin();

			// Create an HQL statement, query the object.
			String sql = "Select e from " + User.class.getName() + " e where e.username=:username ";

			// Create Query object.
			Query<User> query = session.createQuery(sql);

			//set parameters
			query.setParameter("username", username);

			// Execute query.
			Optional<User> employees = query.uniqueResultOptional();

			if(employees.isPresent()) {
				user = employees.get();
			}
			
			// Commit data.
			session.getTransaction().commit();

		} catch (Exception e) {
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(), e);
		} finally {
			session.close();
		}
		
		return user;
	}

}
