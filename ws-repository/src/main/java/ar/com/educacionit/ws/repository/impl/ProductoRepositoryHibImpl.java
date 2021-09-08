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
		
			String sql = "Select p from " + Producto.class.getName() + " p where p.id=:id";
		
			Query<Producto> query = session.createQuery(sql);
		
			query.setParameter("id", id);
		
			Optional<Producto> employees = query.uniqueResultOptional();
			
			if(employees.isPresent()) {
				producto = employees.get();
				
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

	@Override
	public Producto deleteProducto(Long id) throws GenericException {
		
		Producto producto = getById(id);
		Session session = factory.getCurrentSession();
		
		try {			
			session.beginTransaction();
			session.remove(producto);
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(), e);
		} finally {
			session.close();
		}
		return producto;
	}

	@Override
	public Producto update(Producto producto) throws GenericException {
		
		try {
			Session session = factory.getCurrentSession();
			
			try {
				session.getTransaction().begin();
				
				String sql = "Select e from " + Producto.class.getName() + " e where e.codigo=:codigo";
				
				Query<Producto> query = session.createQuery(sql);
				
				query.setParameter("codigo", producto.getCodigo());
				
				Optional<Producto> productoOpcional = query.uniqueResultOptional();
				
				Producto productoBean = null;
				if(productoOpcional.isPresent()) {
					productoBean = productoOpcional.get();
					productoBean.setTitulo(producto.getTitulo());
					productoBean.setPrecio(producto.getPrecio());
					productoBean.setTipoProducto(producto.getTipoProducto());
				}
				
				session.saveOrUpdate(productoBean);
				
				session.getTransaction().commit();
			 
			} catch(ConstraintViolationException e) {
				
				session.getTransaction().rollback();
				
				throw new DuplicatedException(e.getCause().getMessage(), e);
			} catch(Exception e) {
			
				session.getTransaction().rollback();
			
				throw new GenericException(e.getMessage(), e);
			} finally {
				
				session.close();
			}
		return producto;
		
		} catch(DuplicatedException e) {
			throw new GenericException(e.getMessage(), e);
		}
	}
}
