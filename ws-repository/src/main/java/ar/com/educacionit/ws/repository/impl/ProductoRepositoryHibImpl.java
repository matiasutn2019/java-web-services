package ar.com.educacionit.ws.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.exceptions.DuplicatedException;
import ar.com.educacionit.ws.exceptions.GenericException;
import ar.com.educacionit.ws.repository.ProductoRepository;
import ar.com.educacionit.ws.repository.hibernate.HibernateBaseRepository;

public class ProductoRepositoryHibImpl extends HibernateBaseRepository implements ProductoRepository {

	public ProductoRepositoryHibImpl() {
		super();
	}
	
	public Producto getById(Long id) throws GenericException  {
		
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
			
			session.getTransaction().rollback();
			throw new GenericException(e.getCause().getMessage(), e);
		}
		
		return producto;
	}

	public List<Producto> findAll() {
		
		List<Producto> productos = new ArrayList<Producto>();
		
		Session session  = factory.getCurrentSession();
		
		session.beginTransaction();
		
		// HQL
		String sql = "Select p from " + Producto.class.getName() + " p";
		
		Query<Producto> query = session.createQuery(sql);
		
		productos.addAll(query.getResultList());
		
		session.getTransaction().commit();
		
		return productos;
	}

	public Producto insert(Producto productoACrear) throws DuplicatedException, GenericException {
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		try {
			session.saveOrUpdate(productoACrear);
		} catch(ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new DuplicatedException(e.getCause().getMessage(), e);
		} catch(Exception e) {
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(), e);
		} finally {
			session.close();
		}
		
		return productoACrear;
		
	}

}
