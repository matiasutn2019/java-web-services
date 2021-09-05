package ar.com.educacionit.ws.rest.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.exceptions.ServiceException;
import ar.com.educacionit.ws.rest.dto.ProductoRequestDTO;
import ar.com.educacionit.ws.services.ProductoService;
import ar.com.educacionit.ws.services.impl.ProductoServiceImpl;

@Path("productos")
public class ProductoResource {
	
	private ProductoService ps = new ProductoServiceImpl();
		
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllProductos() {
		
		List<Producto> productos = ps.findProductos();
		
		return Response.ok(productos).build();		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	private Response getProducto(@PathParam("id") Long id) {
		
		try {
			Producto producto = this.ps.getProductoById(id);
			if(producto != null) {
				return Response.ok(producto).build();
			} else {
				return Response.status(Status.NOT_FOUND)
						.build();
			}
		} catch(ServiceException se) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(construirErrores(se))
					.build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProducto(ProductoRequestDTO productoRequest) {
		
		Producto productoACrear = new Producto(
				productoRequest.getTitulo(), 
				productoRequest.getCodigo(), 
				productoRequest.getPrecio(),
				productoRequest.getTipoProducto()
				);
		
		Producto productoCreado;
		try {
			productoCreado = this.ps.nuevoProducto(productoACrear);
			return Response.status(Status.CREATED).entity(productoCreado).build();
		} catch (ServiceException se) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(construirErrores(se))
					.build();
		}
			
	}
	
	public Map<String, String> construirErrores(ServiceException se) {
		
		Map<String, String> errores = new HashMap<String, String>();
		if(se.getCause() != null) {
			errores.put("error", se.getCause().getMessage());
		} else {
			errores.put("error", se.getMessage());
		}
		return errores;
	}
}
