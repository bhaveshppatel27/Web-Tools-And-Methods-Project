package com.cinema.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinema.Addons.PDFAndEmailSender;
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
import com.cinema.dao.TicketDAO;
import com.cinema.pojo.Actor;
import com.cinema.pojo.Actress;
import com.cinema.pojo.Auditorium;
import com.cinema.pojo.BankAccount;
import com.cinema.pojo.Customer;
import com.cinema.pojo.Director;
import com.cinema.pojo.Genre;
import com.cinema.pojo.Movie;
import com.cinema.pojo.ShowTimings;
import com.cinema.pojo.Theatre;
import com.cinema.pojo.TheatreAdmin;
import com.cinema.pojo.Ticket;


@Controller
public class AjaxController{

	@Autowired
	AuditoriumDAO auditoriumDAO;

	@Autowired
	MovieDAO movieDAO;

	@Autowired
	ActorDAO actorDAO;

	@Autowired
	ActressDAO actressDAO;

	@Autowired
	DirectorDAO directorDAO;

	@Autowired
	GenreDAO genreDAO;

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	TheatreDAO theatreDAO;

	@Autowired
	ShowTimingsDAO showTimingsDAO;

	@Autowired
	TicketDAO ticketDAO;

	@Autowired
	TheatreAdminDAO theatreAdminDAO;

	@RequestMapping(value = "/logincheck.htm" , method=RequestMethod.POST)
	protected void logincheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();
		HttpSession session =request.getSession();

		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		ArrayList<Customer> customerList = (ArrayList<Customer>) customerDAO.list();
		String s = "false";
		for(Customer c : customerList){
			if(c.getUsername().equalsIgnoreCase(user) && c.getPassword().equals(pass)){
				s="true,"+c.getFirstName() + " " + c.getLastName()+","+c.getRole();
				session.setAttribute("customer", c);
				String rememberMe = request.getParameter("remember");
				if(rememberMe.equalsIgnoreCase("true")){
					Cookie userCookie = new Cookie("uname", user);
					Cookie passwordCookie = new Cookie("upass",pass);
					userCookie.setMaxAge(500000);
					passwordCookie.setMaxAge(500000);
					response.addCookie(userCookie);
					response.addCookie(passwordCookie);
				}

				break;
			}

		}
		out.print(s);
	}

	@RequestMapping(value = "/adminlogincheck.htm" , method=RequestMethod.POST)
	protected void adminlogincheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();
		HttpSession session =request.getSession();

		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		ArrayList<TheatreAdmin> adminList = (ArrayList<TheatreAdmin>) theatreAdminDAO.list();
		String s = "false";
		for(TheatreAdmin ta : adminList){
			if(ta.getUsername().equalsIgnoreCase(user) && ta.getPassword().equals(pass)){
				s="true,"+ta.getUsername()+","+ta.getRole();
				session.setAttribute("theatreadmin", ta);
				String rememberMe = request.getParameter("remember");
				if(rememberMe.equalsIgnoreCase("true")){
					Cookie userCookie = new Cookie("uname", user);
					Cookie passwordCookie = new Cookie("upass",pass);
					userCookie.setMaxAge(500000);
					passwordCookie.setMaxAge(500000);
					response.addCookie(userCookie);
					response.addCookie(passwordCookie);
				}

				AuditoriumDAO auditoriumDAO = new AuditoriumDAO();
				List<Auditorium> auditoriumList= (ArrayList<Auditorium>) auditoriumDAO.getAudiByTheatre(ta.getTheatre().getTheatreid());

				session.setAttribute("audiList", auditoriumList);

				break;
			}

		}




		out.print(s);
	}

	@RequestMapping(value = "/checkcarddetails.htm" , method=RequestMethod.POST)
	protected void checkCardDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();
		HttpSession session =request.getSession();

		int cardnumber = Integer.parseInt(request.getParameter("cardnumber"));
		int pin = Integer.parseInt(request.getParameter("pin"));

		Customer customer = (Customer) session.getAttribute("customer");
		customer = customerDAO.getById(customer.getEmail());
		BankAccount ba = customer.getBankAccount();
		String s = "false";
		if(ba != null){

			if(ba.getCardnumber() == cardnumber && ba.getPin() == pin){
				Ticket ticket = (Ticket) session.getAttribute("ticketonhold");

				ticket.getShowTimings().setCapacity(ticket.getShowTimings().getCapacity() - ticket.getNoOfSeats());

				ticket = ticketDAO.create(ticket);
				Movie movie = ticket.getMovie();
				movie.getBudget().setTotalsales(movie.getBudget().getTotalsales() + 
						(ticket.getNoOfSeats() * ticket.getCost()));
				movieDAO.update(movie);
				s = "true";
				session.removeAttribute("ticketonhold");

				PDFAndEmailSender.createPDF(ticket);
			}
		}

		out.print(s);
	}

	@RequestMapping(value = "/addshowtime.htm" , method=RequestMethod.GET)
	protected String theatrechange(HttpServletRequest request, HttpServletResponse response) throws Exception{


		HttpSession session = request.getSession();
		TheatreAdmin ta = (TheatreAdmin) session.getAttribute("theatreadmin");

		Movie m = movieDAO.getMovieById(Integer.parseInt(request.getParameter("movieid")));
		Auditorium a = auditoriumDAO.get(Integer.parseInt(request.getParameter("audiid")));
		Theatre t = theatreDAO.get(ta.getTheatre().getTheatreid());

		if(m.getTheatres().size()> 0){
			m.getTheatres().add(t);
		}else{
			Set<Theatre> theatreList = new HashSet<Theatre>();
			theatreList.add(t);
			m.setTheatres(theatreList);
		}

		movieDAO.create(m);
		ShowTimings showTimings = new ShowTimings();
		showTimings.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		showTimings.setAudi(a);
		showTimings.setMovie(m);
		showTimings.setTheatre(t);

		//Setting end time for the movie
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = dateFormat.parse(request.getParameter("date"));
		showTimings.setDate(date);
		@SuppressWarnings("deprecation")
		int min = date.getMinutes();
		min = min + m.getLength();

		//System.out.println("Length" + showTimings.getMovie().getLength());

		Date d1 = new Date();
		d1  = (Date) date.clone();
		d1.setMinutes(min);

		//System.out.println("Added Date" + d1);
		showTimings.setEndDate(d1);

		showTimingsDAO.create(showTimings);

		session.setAttribute("message","ShowTiming successfully Updated");
		return "contents";


	}

	/*@RequestMapping(value = "/theatrechange.htm" , method=RequestMethod.GET)
	protected void theatrechange(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		PrintWriter out = response.getWriter();
		int theatreid = Integer.parseInt(request.getParameter("theatreid"));

		List<Auditorium> auditoriumList= (ArrayList<Auditorium>) auditoriumDAO.getAudiByTheatre(theatreid);

		if(auditoriumList != null){
			for(Auditorium a : auditoriumList){
				out.print("<option value='"+a.getAudiid()+"'>"+ a.getName()+"</option>");
			}
		}
	}*/

	/*@RequestMapping(value = "/genrecheck.htm" , method=RequestMethod.GET)
	protected void genrecheck(HttpServletRequest request, HttpServletResponse response) throws Exception {


		PrintWriter out = response.getWriter();

		String genre = request.getParameter("genre");
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
		out.print(s);
	}*/

	/*@RequestMapping(value = "/actorcheck.htm" , method=RequestMethod.GET)
	protected void actorcheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();

		String firstname = request.getParameter("first");
		String lastname = request.getParameter("last");
		String skills = request.getParameter("skills");

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
		out.print(s);
	}


	@RequestMapping(value = "/actresscheck.htm" , method=RequestMethod.GET)
	protected void actresscheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();

		String firstname = request.getParameter("first");
		String lastname = request.getParameter("last");
		String skills = request.getParameter("skills");

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
		out.print(s);
	}

	@RequestMapping(value = "/directorcheck.htm" , method=RequestMethod.GET)
	protected void directorcheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();

		String firstname = request.getParameter("first");
		String lastname = request.getParameter("last");
		int rating = Integer.parseInt(request.getParameter("rating"));

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
		out.print(s);
	}
	 */



	/*@RequestMapping(value = "/usernamecheck.htm" , method=RequestMethod.GET)
	protected void usernamecheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();

		String user = request.getParameter("username");

		ArrayList<Customer> customerList = (ArrayList<Customer>) customerDAO.list();
		String s = "false";
		for(Customer c : customerList){
			if(c.getUsername().equalsIgnoreCase(user)){
				s="true";
				break;
			}

		}
		out.print(s);
	}*/


	/*@RequestMapping(value = "/emailidcheck.htm" , method=RequestMethod.GET)
	protected void emailidCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");

		ArrayList<Customer> customerList = (ArrayList<Customer>) customerDAO.list();
		String s = "false";
		for(Customer c : customerList){
			if(c.getEmail().equalsIgnoreCase(email)){
				s="true";
				break;
			}

		}
		out.print(s);
	}*/

	/*	@RequestMapping(value = "/checkmovietitle.htm" , method=RequestMethod.GET)
	protected void movieTitleCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();

		String title = request.getParameter("title");

		ArrayList<Movie> movieList = (ArrayList<Movie>) movieDAO.list();
		String s = "false";
		for(Movie m : movieList){
			if(m.getTitle().equalsIgnoreCase(title.trim())){
				s="true";
				break;
			}

		}
		out.print(s);
	}*/

	/*@RequestMapping(value = "/checktheatre.htm" , method=RequestMethod.GET)
	protected void theatreCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String location = request.getParameter("location");

		ArrayList<Theatre> theatreList = (ArrayList<Theatre>) theatreDAO.list();
		String s = "false";
		for(Theatre t : theatreList){
			if(t.getName().equalsIgnoreCase(name.trim()) && t.getLocation().getLocate().equalsIgnoreCase(location)){
				s="true";
				break;
			}

		}
		out.print(s);
	}*/

	/*@RequestMapping(value = "/checkshowtimeclash.htm" , method=RequestMethod.GET)
	protected void checkShowTimeClash(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();

		String date = request.getParameter("date");
		int audiid = Integer.parseInt(request.getParameter("audiid"));
		int movieid = Integer.parseInt(request.getParameter("movieid"));

		Movie movie = movieDAO.getMovieById(movieid);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date newStartDate = dateFormat.parse(date);

		@SuppressWarnings("deprecation")
		int min = newStartDate.getMinutes();
		min = min + movie.getLength();

		Date newEndDate = (Date) newStartDate.clone();
		newEndDate.setMinutes(min);


		ArrayList<ShowTimings> showTimingsList = (ArrayList<ShowTimings>) showTimingsDAO.getShowTimeByAudi(audiid);
		String s = "false";
		for(ShowTimings st : showTimingsList){
			System.out.println(st.getShowtimeid() + "sdfdf"  + st.getAudi().getAudiid());
			if((isDateWithinRange(newStartDate, st.getDate(), st.getEndDate())) || (isDateWithinRange(newEndDate, st.getDate(), st.getEndDate()))){
				s="true";
				break;
			}

		}
		out.print(s);
	}*/

	/*public boolean isDateWithinRange(Date testDate,Date startDate,Date endDate) {
		   return !(testDate.before(startDate) || testDate.after(endDate));
		}*/


}