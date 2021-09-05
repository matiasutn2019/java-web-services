package ar.com.educacionit.ws.repository;

import java.util.List;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.exceptions.DuplicatedException;
import ar.com.educacionit.ws.exceptions.GenericException;

public interface ProductoRepository {

	public Producto getById(Long id) throws GenericException;

	public List<Producto> findAll();
	
	public Producto insert(Producto productoACrear) throws DuplicatedException, GenericException;
}
