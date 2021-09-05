package ar.com.educacionit.ws.rest.dto;

public class ProductoRequestDTO {

	private String titulo;
	
	private String codigo;
	
	private Float precio;
	
	private Long tipoProducto;

	public ProductoRequestDTO() {
		
	}

	

	public ProductoRequestDTO(String titulo, String codigo, Float precio, Long tipoProducto) {
		super();
		this.titulo = titulo;
		this.codigo = codigo;
		this.precio = precio;
		this.tipoProducto = tipoProducto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Long getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(Long tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
		
}
