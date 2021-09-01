package ar.com.educacionit.ws.repository.impl;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import ar.com.educacionit.ws.domain.HibernateUtils;
import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.repository.ProductoRepository;

public class ProductoRepositoryHibImpl implements ProductoRepository {

	public Producto getById(Long id) {
		
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();
		
		Producto producto = null;
		
		try {		
			session.getTransaction().begin();
		
			String sql = "Select p from " + Producto.class.getName() + " p where p.id = :id";
		
			Query<Producto> query = session.createQuery(sql);
		
			query.setParameter("id", id);
			//query.setParameter("id", new Long(1));
			//si no se le pasa el valor como obj Long, Java supone q es un Integer y da error de casteo
		
			Optional<Producto> employees = query.uniqueResultOptional();
			
			if(employees.isPresent()) {
				producto = employees.get();
				
				//System.out.println("Nombre: " + producto.getTitulo().toUpperCase() + ". Precio: " + producto.getPrecio());
			}
			
			session.getTransaction().commit();
		
		} catch(Exception e) {
			
			e.printStackTrace();
			
			session.getTransaction().rollback();
		}
		

		return producto;
	}

}
