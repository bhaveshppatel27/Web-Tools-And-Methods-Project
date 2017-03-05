package com.cinema.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.dao.ActorDAO;
import com.cinema.dao.ActressDAO;
import com.cinema.dao.AuditoriumDAO;
import com.cinema.dao.CustomerDAO;
import com.cinema.dao.DirectorDAO;
import com.cinema.dao.GenreDAO;
import com.cinema.dao.MovieDAO;
import com.cinema.dao.ShowTimingsDAO;
import com.cinema.dao.TheatreDAO;
import com.cinema.dao.TicketDAO;
import com.cinema.pojo.Auditorium;
import com.cinema.pojo.Customer;
import com.cinema.pojo.Location;
import com.cinema.pojo.Movie;
import com.cinema.pojo.ShowTimings;
import com.cinema.pojo.Theatre;
import com.cinema.pojo.Ticket;


@Controller
public class QuickBookController{

	@Autowired
	MovieDAO movieDAO;

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	ShowTimingsDAO showTimingsDAO;


	@RequestMapping(value = "/displaymoviebasedlocations.htm" , method=RequestMethod.GET)
	protected void movieChange(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		PrintWriter out = response.getWriter();
		int movieid = Integer.parseInt(request.getParameter("movieid"));

		List<ShowTimings> showTimingsList= (ArrayList<ShowTimings>) showTimingsDAO.getShowTimeByMovieId(movieid);
		Set<Location> locationList = new HashSet<Location>();
		if(showTimingsList != null){
			for(ShowTimings s : showTimingsList){
				Location l = s.getTheatre().getLocation();
				locationList.add(l);
			}
			//out.print("<option class='bs-title-option' value=''>Select Location</option>");
			for(Location l : locationList){
				out.print("<option class = 'bs-title-option' value='"+l.getLocationid()+"'>"+ l.getLocate()+"</option>");
			}
		}

	}


	@RequestMapping(value = "/displaylocationbasedtheatres.htm" , method=RequestMethod.GET)
	protected void locationChange(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		PrintWriter out = response.getWriter();
		int movieid = Integer.parseInt(request.getParameter("movieid"));
		int locationid = Integer.parseInt(request.getParameter("locationid"));

		List<ShowTimings> showTimingsList= (ArrayList<ShowTimings>) showTimingsDAO.getShowTimeByMovieId(movieid);
		Set<Theatre> theatreList = new HashSet<Theatre>();
		if(showTimingsList != null){
			for(ShowTimings s : showTimingsList){

				if(s.getMovie().getMovieid() == movieid && s.getTheatre().getLocation().getLocationid() == locationid){
					Theatre t = s.getTheatre();
					theatreList.add(t);
				}
			}

			for(Theatre t : theatreList){
				out.print("<option class = 'bs-title-	' value='"+t.getTheatreid()+"'>"+ t.getName()+"</option>");
			}

		}

	}

	@RequestMapping(value = "/displaytheatrebasedshowtimings.htm" , method=RequestMethod.GET)
	protected void theatreChange(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		PrintWriter out = response.getWriter();
		int movieid = Integer.parseInt(request.getParameter("movieid"));
		int locationid = Integer.parseInt(request.getParameter("locationid"));
		int theatreid = Integer.parseInt(request.getParameter("theatreid"));

		List<ShowTimings> showTimingsList= (ArrayList<ShowTimings>) showTimingsDAO.getShowTimeByMovieId(movieid);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = new Date();
		dateFormat.format(date);
		if(showTimingsList != null){
			for(ShowTimings s : showTimingsList){
				if(s.getDate().after(date)){
					if(s.getMovie().getMovieid() == movieid && s.getTheatre().getLocation().getLocationid() == locationid
							&& s.getTheatre().getTheatreid() == theatreid){
						out.print("<option class = 'bs-title-option' value='"+s.getShowtimeid()+"'>"+ s.getDate()+"</option>");
					}
				}
			}

		}

	}


	@RequestMapping(value = "/bookticket.htm" , method=RequestMethod.GET)
	protected void bookTicket(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		PrintWriter out = response.getWriter();
		Customer customer = (Customer) session.getAttribute("customer");
		
		if(customer == null){
			out.println("false,false");
			return;
		}


		int movieid = Integer.parseInt(request.getParameter("movieid"));
		int locationid = Integer.parseInt(request.getParameter("locationid"));
		int theatreid = Integer.parseInt(request.getParameter("theatreid"));
		int showTimeId = Integer.parseInt(request.getParameter("showTimeId"));
		int cost = Integer.parseInt(request.getParameter("cost"));
		int seat = Integer.parseInt(request.getParameter("seat"));	

		customer = customerDAO.getById(customer.getEmail());
		ShowTimings showTimings = showTimingsDAO.get(showTimeId);
		System.out.println("-----" + showTimings.getCapacity());
		if(showTimings.getCapacity() < seat){
			out.print("false,"+showTimings.getCapacity());
			return;
		}

		//showTimings.setCapacity(showTimings.getCapacity() - seat);
		Hibernate.initialize(showTimings.getAudi());
		Ticket ticket = new Ticket();
		ticket.setAudino(showTimings.getAudi().getAudiid());
		ticket.setMovie(movieDAO.getMovieById(movieid));
		ticket.setCustomer(customer);
		ticket.setCost(cost);
		ticket.setDate(showTimings.getDate());
		ticket.setNoOfSeats(seat);
		ticket.setShowTimings(showTimings);



		session.setAttribute("ticketonhold", ticket);


		out.print("true,");

	}

	@RequestMapping(value = "/entercarddetails.htm" , method=RequestMethod.GET)
	protected String enterCardDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "addcarddetails";
	}


}