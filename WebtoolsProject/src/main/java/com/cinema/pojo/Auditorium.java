package com.cinema.pojo;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="auditorium")
public class Auditorium {

	@Id
	@GeneratedValue
	@Column(name="audiid")
	private int audiid;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="theatre")
	private Theatre theatre;

	@Column(name="name")
	private String name;
	
	public int getAudiid() {
		return audiid;
	}


	public void setAudiid(int audiid) {
		this.audiid = audiid;
	}


	public Theatre getTheatre() {
		return theatre;
	}


	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
