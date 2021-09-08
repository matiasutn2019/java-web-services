package ar.com.educacionit.ws.services;

import java.util.List;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.exceptions.ServiceException;

public interface ProductoService {
	
	public Producto obtenerProducto(Long id);

	public List<Producto> findProductos() throws ServiceException;

	public Producto nuevoProducto(Producto productoACrear) throws ServiceException;

	public Producto getProductoById(Long id) throws ServiceException;

	public Producto eliminarProducto(Long id) throws ServiceException;

	public Producto updateProducto(Producto producto) throws ServiceException;

}
