package ar.com.educacionit.ws.repository;

import ar.com.educacionit.ws.domain.Producto;

public interface ProductoRepository {

	public Producto getById(Long id);
}
