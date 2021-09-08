package ar.com.educacionit.ws.domain;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
	private Long id;
	
	@Column(name = "role", nullable = false, unique = true)
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
