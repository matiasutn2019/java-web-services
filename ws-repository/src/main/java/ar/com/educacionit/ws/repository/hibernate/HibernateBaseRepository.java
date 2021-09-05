package ar.com.educacionit.ws.repository.hibernate;

import org.hibernate.SessionFactory;

public class HibernateBaseRepository {
	
	protected SessionFactory factory;

	public HibernateBaseRepository() {
		
		this.factory = HibernateUtils.getSessionFactory();
	}	

}
