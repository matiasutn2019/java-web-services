package ar.com.educacionit.ws.services;

import java.util.List;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.exceptions.ServiceException;
import ar.com.educacionit.ws.services.impl.ProductoServiceImpl;

public class TestProductoRepository {

	public static void main(String[] args) {
		
		ProductoService ps = new ProductoServiceImpl();
		List<Producto> p = null;;
		try {
			p = ps.findProductos();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.stream().forEach(x -> System.out.println(x));
	}

}