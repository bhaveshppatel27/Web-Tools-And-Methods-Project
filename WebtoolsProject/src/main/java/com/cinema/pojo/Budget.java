package com.cinema.pojo;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "budget")
public class Budget {
	
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "movie"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "budgetid", unique = true, nullable = false)
	private int budgetid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="budgetid")
	private Movie movie;
	
	@Column(name="makingcost")
	private int makingcost;
	
	@Column(name="totalsales")
	private int totalsales;

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	

	public int getBudgetid() {
		return budgetid;
	}

	public void setBudgetid(int budgetid) {
		this.budgetid = budgetid;
	}

	public int getMakingcost() {
		return makingcost;
	}

	public void setMakingcost(int makingcost) {
		this.makingcost = makingcost;
	}

	public int getTotalsales() {
		return totalsales;
	}

	public void setTotalsales(int totalsales) {
		this.totalsales = totalsales;
	}
	
	
}
