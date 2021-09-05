package ar.com.educacionit.ws.services;

import java.util.List;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.services.impl.ProductoServiceImpl;

public class TestProductoRepository {

	public static void main(String[] args) {
		
		ProductoService ps = new ProductoServiceImpl();
		List<Producto> p = ps.findProductos();
		
		p.stream().forEach(x -> System.out.println(x));
	}

}