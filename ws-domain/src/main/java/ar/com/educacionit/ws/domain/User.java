package ar.com.educacionit.ws.domain;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	private Long id;
	
	@Column(name = "usename", nullable = false, unique = true)
	private String username;
	
	@Column(name = "usename", nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
				name = "user_role",
				joinColumns = {
					@JoinColumn(name = "user_id")	
				},
				inverseJoinColumns = {
					@JoinColumn(name = "role_id")
				}
			)
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
