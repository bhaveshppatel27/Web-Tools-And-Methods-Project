package com.cinema.Addons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cinema.dao.ActorDAO;
import com.cinema.dao.ActressDAO;
import com.cinema.dao.AuditoriumDAO;
import com.cinema.dao.CustomerDAO;
import com.cinema.dao.DirectorDAO;
import com.cinema.dao.GenreDAO;
import com.cinema.dao.MovieDAO;
import com.cinema.dao.ShowTimingsDAO;
import com.cinema.dao.TheatreAdminDAO;
import com.cinema.dao.TheatreDAO;
import com.cinema.exception.Excep;
import com.cinema.pojo.Actor;
import com.cinema.pojo.Actress;
import com.cinema.pojo.Auditorium;
import com.cinema.pojo.Customer;
import com.cinema.pojo.Director;
import com.cinema.pojo.Genre;
import com.cinema.pojo.Movie;
import com.cinema.pojo.ShowTimings;
import com.cinema.pojo.Theatre;
import com.cinema.pojo.TheatreAdmin;

public class RemoteService {
	public String checkGenre(String genre) throws Excep
	{
		GenreDAO genreDAO = new GenreDAO();
		ArrayList<Genre> genreList = (ArrayList<Genre>) genreDAO.list();
		String s = "false";
		for(Genre g : genreList){
			if(g.getName().equalsIgnoreCase(genre)){
				s="true";
				break;
			}

		}
		if(s == "false"){
			Genre g1 = new Genre();
			g1.setName(genre);
			genreDAO.create(g1);
		}
		return s;
	}
	
	public String checkActor(String firstname,String lastname, String skills) throws Excep
	{
		ActorDAO actorDAO = new ActorDAO();
		ArrayList<Actor> actorList = (ArrayList<Actor>) actorDAO.list();
		String s = "false";
		for(Actor a : actorList){
			if(a.getFirstName().equalsIgnoreCase(firstname) && a.getLastName().equalsIgnoreCase(lastname)){
				s="true";
				break;
			}

		}
		if(s == "false"){
			Actor a1 = new Actor();
			a1.setFirstName(firstname);
			a1.setLastName(lastname);
			a1.setSkills(skills);
			actorDAO.create(a1);
		}
		System.out.println("hello");
		return s;
		
		
		
	}
	
	public String checkActress(String firstname,String lastname, String skills) throws Excep
	{
		ActressDAO actressDAO = new ActressDAO();
		ArrayList<Actress> actressList = (ArrayList<Actress>) actressDAO.list();
		String s = "false";
		for(Actress a : actressList){
			if(a.getFirstName().equalsIgnoreCase(firstname) && a.getLastName().equalsIgnoreCase(lastname)){
				s="true";
				break;
			}
 
		}
		if(s == "false"){
			Actress a1 = new Actress();
			a1.setFirstName(firstname);
			a1.setLastName(lastname);
			a1.setSkills(skills);
			actressDAO.create(a1);
		}
		return s;
	}
	
	public String checkDirector(String firstname,String lastname, int rating) throws Excep
	{
		
		DirectorDAO directorDAO = new DirectorDAO();
		ArrayList<Director> directorList = (ArrayList<Director>) directorDAO.list();
		String s = "false";
		for(Director d : directorList){
			if(d.getFirstName().equalsIgnoreCase(firstname) && d.getLastName().equalsIgnoreCase(lastname)){
				s="true";
				break;
			}

		}
		if(s == "false"){
			Director d1 = new Director();
			d1.setFirstName(firstname);
			d1.setLastName(lastname);
			d1.setRating(rating);

			directorDAO.create(d1);
		}
		return s;
		
		
	}
	
	
	public String checkMovieTitle(String title) throws Excep
	{
		
		MovieDAO movieDAO = new MovieDAO();
		ArrayList<Movie> movieList = (ArrayList<Movie>) movieDAO.list();
		String s = "false";
		for(Movie m : movieList){
			if(m.getTitle().equalsIgnoreCase(title.trim())){
				s="true";
				break;
			}

		}
		return s;
		
		
	}
	
	public String checkTheatre(String name, String location) throws Excep
	{
		TheatreDAO theatreDAO = new TheatreDAO();
		ArrayList<Theatre> theatreList = (ArrayList<Theatre>) theatreDAO.list();
		String s = "false";
		for(Theatre t : theatreList){
			if(t.getName().equalsIgnoreCase(name.trim()) && t.getLocation().getLocate().equalsIgnoreCase(location)){
				s="true";
				break;
			}

		}
		
		return s;
		
		
	}
	
	public String checkUsername(String user) throws Excep
	{
		CustomerDAO customerDAO = new CustomerDAO();
		ArrayList<Customer> customerList = (ArrayList<Customer>) customerDAO.list();
		String s = "false";
		for(Customer c : customerList){
			if(c.getUsername().equalsIgnoreCase(user)){
				s="true";
				break;
			}

		}
		
		return s;
	
	}
	
	public String checkTheatreAdminUsername(String user) throws Excep
	{
		TheatreAdminDAO theatreAdminDAO = new TheatreAdminDAO();
		ArrayList<TheatreAdmin> adminList = (ArrayList<TheatreAdmin>) theatreAdminDAO.list();
		String s = "false";
		for(TheatreAdmin ta : adminList){
			if(ta.getUsername().equalsIgnoreCase(user)){
				s="true";
				break;
			}

		}
		
		return s;
	
	}
	
	
	public String checkEmail(String email) throws Excep
	{
		CustomerDAO customerDAO = new CustomerDAO();
		ArrayList<Customer> customerList = (ArrayList<Customer>) customerDAO.list();
		String s = "false";
		for(Customer c : customerList){
			if(c.getEmail().equalsIgnoreCase(email)){
				s="true";
				break;
			}

		}
		
		return s;
	
	}
	
	public String checkShowTimeClash(String date, int audiid, int movieid) throws Excep
	{
		
		MovieDAO movieDAO = new MovieDAO();
		ShowTimingsDAO showTimingsDAO = new ShowTimingsDAO();
		Movie movie = movieDAO.getMovieById(movieid);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date newStartDate = null;
		try {
			newStartDate = dateFormat.parse(date);
		} catch (ParseException e) {
			System.out.println("ParseException in date" + e);
		}
		
		@SuppressWarnings("deprecation")
		int min = newStartDate.getMinutes();
		min = min + movie.getLength();
		
		Date newEndDate = (Date) newStartDate.clone();
		newEndDate.setMinutes(min);

		
		ArrayList<ShowTimings> showTimingsList = (ArrayList<ShowTimings>) showTimingsDAO.getShowTimeByAudi(audiid);
		String s = "false";
		for(ShowTimings st : showTimingsList){

			if((isDateWithinRange(newStartDate, st.getDate(), st.getEndDate())) || (isDateWithinRange(newEndDate, st.getDate(), st.getEndDate()))){
				s="true";
				break;
			}

		}
		
		return s;
		
		
	}
	
	public String populateAuditorium(int theatreid) throws Excep
	{
		AuditoriumDAO auditoriumDAO = new AuditoriumDAO();
		List<Auditorium> auditoriumList= (ArrayList<Auditorium>) auditoriumDAO.getAudiByTheatre(theatreid);
		StringBuffer sBuffer = new StringBuffer();
		if(auditoriumList != null){
			for(Auditorium a : auditoriumList){
				sBuffer.append("<option value='"+a.getAudiid()+"'>"+ a.getName()+"</option>");
			}
		}
		
		return sBuffer.toString();
	}
	
	public boolean isDateWithinRange(Date testDate,Date startDate,Date endDate) {
		   return !(testDate.before(startDate) || testDate.after(endDate));
		}
	
}
