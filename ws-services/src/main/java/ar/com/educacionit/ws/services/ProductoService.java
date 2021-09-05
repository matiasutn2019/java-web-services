package ar.com.educacionit.ws.services;

import java.util.List;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.exceptions.ServiceException;

public interface ProductoService {

	public List<Producto> findProductos();

	public Producto nuevoProducto(Producto productoACrear) throws ServiceException;

	public Producto getProductoById(Long id) throws ServiceException;

}
