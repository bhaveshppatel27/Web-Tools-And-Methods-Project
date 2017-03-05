package com.cinema.pojo;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="ticket")
public class Ticket {

	@Id
	@GeneratedValue
	@Column(name="ticketid", unique = true, nullable = false)
	private int ticketid;
	
	@Column(name = "bookingdate")
	private Date bookingdate = new Date();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="movie")
	private Movie movie;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="customer")
	private Customer customer;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="showTimings")
	private ShowTimings showTimings;
	
	@Column(name="cost")
	private int cost;
	
	@Column(name="audino")
	private int audino;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="noofseats")
	private int noOfSeats;
	
	public Ticket(){
		
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getTicketid() {
		return ticketid;
	}


	public void setTicketid(int ticketid) {
		this.ticketid = ticketid;
	}


	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public int getAudino() {
		return audino;
	}

	public void setAudino(int audino) {
		this.audino = audino;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ShowTimings getShowTimings() {
		return showTimings;
	}

	public void setShowTimings(ShowTimings showTimings) {
		this.showTimings = showTimings;
	}

	public Date getBookingdate() {
		return bookingdate;
	}

	public void setBookingdate(Date bookingdate) {
		this.bookingdate = bookingdate;
	}
	
	
	
}
