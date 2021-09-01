package ar.com.educacionit.ws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "titulo", nullable = false, length = 50)
	private String titulo;
	
	@Column(name = "codigo", nullable = false, unique = true, length = 6)
	private String codigo;
	
	@Column(name = "precio", nullable = false)
	private Float precio;

	public Producto() {
		super();
	}

	public Producto(Long id, String titulo, String codigo, Float precio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.codigo = codigo;
		this.precio = precio;
	}

	public Producto(String titulo, String codigo, Float precio) {
		super();
		this.titulo = titulo;
		this.codigo = codigo;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Producto [id=" + id + ", titulo=" + titulo + ", codigo=" + codigo + ", precio=" + precio + "]";
	}
		
}
