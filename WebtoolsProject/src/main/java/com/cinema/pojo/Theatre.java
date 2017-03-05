package com.cinema.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "theatre")
public class Theatre {

	@Id @GeneratedValue
	@Column(name = "theatreid", nullable = false , unique = true)
	private int theatreid;
	
	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "theatres")
	Set<Movie> movies = new HashSet<Movie>();
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="theatre", cascade=CascadeType.ALL)
    private TheatreAdmin theatreAdmin;
	
	@Transient
	private int noOfAudi;
	
	@Transient
	private String locate;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "location")
	private Location location;
	
	public int getTheatreid() {
		return theatreid;
	}

	public void setTheatreid(int theatreid) {
		this.theatreid = theatreid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public int getNoOfAudi() {
		return noOfAudi;
	}

	public void setNoOfAudi(int noOfAudi) {
		this.noOfAudi = noOfAudi;
	}

	public String getLocate() {
		return locate;
	}

	public void setLocate(String locate) {
		this.locate = locate;
	}

	public TheatreAdmin getTheatreAdmin() {
		return theatreAdmin;
	}

	public void setTheatreAdmin(TheatreAdmin theatreAdmin) {
		this.theatreAdmin = theatreAdmin;
	}

	
	
}
