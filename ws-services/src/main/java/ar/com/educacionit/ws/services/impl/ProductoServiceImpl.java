package ar.com.educacionit.ws.services.impl;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.repository.ProductoRepository;
import ar.com.educacionit.ws.repository.impl.ProductoRepositoryHibImpl;
import ar.com.educacionit.ws.services.ProductoService;

public class ProductoServiceImpl implements ProductoService {

	private ProductoRepository productoRepository;
	
	public ProductoServiceImpl() {
		
		this.productoRepository = new ProductoRepositoryHibImpl();
	}
	
	public Producto obtenerProducto(Long id) {
		
		return this.productoRepository.getById(id);
	}

}
