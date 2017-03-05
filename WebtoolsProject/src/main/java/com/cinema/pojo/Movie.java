package com.cinema.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="movie")
public class Movie {

	@Id @GeneratedValue
	@Column(name = "movieid", nullable = false , unique = true)
	private int movieid;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "length")
	private int length;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "category")
	private String category;

	@OneToOne(fetch=FetchType.LAZY, mappedBy="movie", cascade=CascadeType.ALL)
    private Budget budget;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "movie_actor",
	joinColumns = {@JoinColumn(name = "movieid", nullable = false, updatable = false) }, 
	inverseJoinColumns = { @JoinColumn(name = "personid",nullable = false, updatable = false) })
	private Set<Actor> actors = new HashSet<Actor>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "movie_actress",
	joinColumns = {@JoinColumn(name = "movieid", nullable = false, updatable = false) }, 
	inverseJoinColumns = { @JoinColumn(name = "personid",nullable = false, updatable = false) })
	private Set<Actress> actress = new HashSet<Actress>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "movie_director",
	joinColumns = {@JoinColumn(name = "movieid", nullable = false, updatable = false) }, 
	inverseJoinColumns = { @JoinColumn(name = "personid",nullable = false, updatable = false) })
	private Set<Director> directors = new HashSet<Director>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "movie_theatre",
	joinColumns = {@JoinColumn(name = "movieid", nullable = false, updatable = false) }, 
	inverseJoinColumns = { @JoinColumn(name = "theatreid",nullable = false, updatable = false) })
	private Set<Theatre> theatres  = new HashSet<Theatre>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "genre")
	private Genre genre;
	
	@Column(name="photoname")
	private String photoName;
	
	@Transient
	private MultipartFile photo;
	
	@Transient
	private int gen;
	
	@Transient
	private ArrayList<Integer> act;
	
	@Transient
	private ArrayList<Integer> actr;
	
	@Transient
	private ArrayList<Integer> dir;
	
	public Movie(){
		
		
	}
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Actor> getActors() {
		return actors;
	}
	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
	public Set<Actress> getActress() {
		return actress;
	}
	public void setActress(Set<Actress> actress) {
		this.actress = actress;
	}
	public Set<Director> getDirectors() {
		return directors;
	}
	public void setDirectors(Set<Director> directors) {
		this.directors = directors;
	}
	public Set<Theatre> getTheatres() {
		return theatres;
	}
	public void setTheatres(Set<Theatre> theatres) {
		this.theatres = theatres;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public int getGen() {
		return gen;
	}
	public void setGen(int gen) {
		this.gen = gen;
	}
	public ArrayList<Integer> getAct() {
		return act;
	}
	public void setAct(ArrayList<Integer> act) {
		this.act = act;
	}
	public ArrayList<Integer> getActr() {
		return actr;
	}
	public void setActr(ArrayList<Integer> actr) {
		this.actr = actr;
	}
	public ArrayList<Integer> getDir() {
		return dir;
	}
	public void setDir(ArrayList<Integer> dir) {
		this.dir = dir;
	}
	public Budget getBudget() {
		return budget;
	}
	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

	
}

