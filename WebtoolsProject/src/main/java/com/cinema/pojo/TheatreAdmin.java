package com.cinema.pojo;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "theatreadmin")
public class TheatreAdmin {
	
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "theatre"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "theatreadminid", unique = true, nullable = false)
	private int theatreadminid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="theatreadminid")
	private Theatre theatre;

	@Column(name ="username")
	private String username;
	
	@Column(name ="password")
	private String password;
	
	@Column(name ="role")
	private String role;

	public int getTheatreadminid() {
		return theatreadminid;
	}

	public void setTheatreadminid(int theatreadminid) {
		this.theatreadminid = theatreadminid;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

	
	
	
	
	
}
