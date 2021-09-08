package ar.com.educacionit.ws.rest.resources;

public enum ProductoRestWSErrorEnum {
	ID_REQUERIDO(1, "DEBE INDICAR EL ID"),
	PRECIO(2, "DEBE INDICAR EL PRECIO"),
	CODIGO(3, "DEBE INDICAR EL CODIGO"),
	TITULO(4, "DEBE INDICAR EL TITUTLO"),
	TIPO_PRODUCTO(5, "DEBE INDICAR EL TIPO DE PRODUCTO"), 
	ID_INEXISTENTE(6, "PRODUCTO INEXISTENTE");
	
	private Integer id;
	private String msj;
	
	private ProductoRestWSErrorEnum(Integer id, String msj) {
		this.id = id;
		this.msj = msj;
	}

	public Integer getId() {
		return id;
	}

	public String getMsj() {
		return msj;
	}

}
