package com.cinema.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "actor")
@PrimaryKeyJoinColumn(name="personid")
public class Actor extends Person {
	
	@Column(name = "skills")
	private String skills;
	
	public Actor(){
		
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "actors")
	private Set<Movie> movies = new HashSet<Movie>();

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	
}
