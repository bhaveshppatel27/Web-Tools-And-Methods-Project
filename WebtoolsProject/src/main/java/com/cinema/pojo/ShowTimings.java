package com.cinema.pojo;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="showtimings")
public class ShowTimings {
	
	@Id @GeneratedValue
	@Column(name  = "showtimeid")
	private int showtimeid;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "movie")
	private Movie movie;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "audi")
	private Auditorium audi;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="enddate")
	private Date endDate;
	
	@Column(name="capacity")
	private int capacity;
	
	@Transient
	private int movieid;
	
	@Transient
	private int audiid;
	
	@Transient
	private int theatreid;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "theatre")
	private Theatre theatre;

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Auditorium getAudi() {
		return audi;
	}

	public void setAudi(Auditorium audi) {
		this.audi = audi;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getShowtimeid() {
		return showtimeid;
	}

	public void setShowtimeid(int showtimeid) {
		this.showtimeid = showtimeid;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public int getAudiid() {
		return audiid;
	}

	public void setAudiid(int audiid) {
		this.audiid = audiid;
	}

	public int getTheatreid() {
		return theatreid;
	}

	public void setTheatreid(int theatreid) {
		this.theatreid = theatreid;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
