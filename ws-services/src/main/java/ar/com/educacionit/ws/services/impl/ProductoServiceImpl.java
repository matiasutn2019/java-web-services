package ar.com.educacionit.ws.services.impl;

import java.util.List;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.exceptions.DuplicatedException;
import ar.com.educacionit.ws.exceptions.GenericException;
import ar.com.educacionit.ws.exceptions.ServiceException;
import ar.com.educacionit.ws.repository.ProductoRepository;
import ar.com.educacionit.ws.repository.impl.ProductoRepositoryHibImpl;
import ar.com.educacionit.ws.services.ProductoService;

public class ProductoServiceImpl implements ProductoService {

	private ProductoRepository productoRepository;
	
	public ProductoServiceImpl() {
		
		this.productoRepository = new ProductoRepositoryHibImpl();
	}

	public List<Producto> findProductos() {
		
		return this.productoRepository.findAll();
	}

	public Producto nuevoProducto(Producto productoACrear) throws ServiceException {
		
		try {
			return this.productoRepository.insert(productoACrear);
		} catch (DuplicatedException e) {
			throw new ServiceException("Producto duplicado " + e.getMessage(), e);
		} catch (GenericException e) {
			throw new ServiceException("No se pudo crear el producto ", e);
		}
	}

	@Override
	public Producto getProductoById(Long id) throws ServiceException {
		try {
			return this.productoRepository.getById(id);
		} catch (GenericException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
