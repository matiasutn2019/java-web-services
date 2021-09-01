package ar.com.educacionit.ws.services;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.services.impl.ProductoServiceImpl;

public class TestProductoRepository {

	public static void main(String[] args) {
		ProductoService ps = new ProductoServiceImpl();
		Producto p = ps.obtenerProducto(1l);
		if(p != null) {
			System.err.println(p);
		}

	}

}
