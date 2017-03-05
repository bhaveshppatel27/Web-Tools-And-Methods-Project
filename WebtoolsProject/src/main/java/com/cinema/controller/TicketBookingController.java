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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import com.cinema.pojo.Customer;
import com.cinema.pojo.Location;
import com.cinema.pojo.ShowTimings;
import com.cinema.pojo.Theatre;
import com.cinema.pojo.Ticket;


@Controller
public class TicketBookingController{

	@Autowired
	MovieDAO movieDAO;

	@Autowired
	ShowTimingsDAO showTimingsDAO;

	@Autowired
	TicketDAO ticketDAO;

	@RequestMapping(value = "/displaymoviebasedlocations1.htm" , method=RequestMethod.GET)
	protected void movieChange(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		PrintWriter out = response.getWriter();
		int movieid = Integer.parseInt(request.getParameter("movieid"));

		List<ShowTimings> showTimingsList= (ArrayList<ShowTimings>) showTimingsDAO.getShowTimeByMovieId(movieid);
		Set<Location> locationList = null;
		if(showTimingsList != null){
			locationList = new HashSet<Location>();
			for(ShowTimings s : showTimingsList){
				Location l = s.getTheatre().getLocation();
				locationList.add(l);
			}
			out.print("<span><strong>Select Location ==> </strong></span>");
			for(Location l : locationList){
				out.print("<label class='radio-inline'><input type='radio'name='locationid' onclick='showtheatres(this.value)' value='"+l.getLocationid()+"'>"+l.getLocate()+"</label>");
			}
		}

	}


	@RequestMapping(value = "/displaylocationbasedtheatres1.htm" , method=RequestMethod.GET)
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
			out.print("<span><strong>Select Theatre ==> </strong></span>");
			for(Theatre t : theatreList){
				out.print("<label class='radio-inline'><input type='radio'name='theatreid' onclick='showtimings(this.value)' value='"+t.getTheatreid()+"'>"+t.getName()+"</label>");
			}

		}

	}

	@RequestMapping(value = "/displaytheatrebasedshowtimings1.htm" , method=RequestMethod.GET)
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
			out.print("<span><strong>Select Showtimings ==> </strong></span>");
			for(ShowTimings s : showTimingsList){
				if(s.getDate().after(date)){
					if(s.getMovie().getMovieid() == movieid && s.getTheatre().getLocation().getLocationid() == locationid
							&& s.getTheatre().getTheatreid() == theatreid){
						out.print("<label class='radio-inline'><input type='radio'name='showtimeid' onclick='showprice()' value='"+s.getShowtimeid()+"'>"+s.getDate()+"</label>");
					}
				}
			}

		}

	}


	@RequestMapping(value = "/bookticket1.htm" , method=RequestMethod.GET)
	protected void bookTicket(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		PrintWriter out = response.getWriter();
		Customer customer = (Customer) session.getAttribute("customer");
		if(customer == null){
			out.println("false");
			return;
		}
		
		int movieid = Integer.parseInt(request.getParameter("movieid"));
		int locationid = Integer.parseInt(request.getParameter("locationid"));
		int theatreid = Integer.parseInt(request.getParameter("theatreid"));
		int showTimeId = Integer.parseInt(request.getParameter("showTimeId"));
		int cost = Integer.parseInt(request.getParameter("cost"));
		int seat = Integer.parseInt(request.getParameter("seat"));	

		ShowTimings showTimings = showTimingsDAO.get(showTimeId);
		if(showTimings.getCapacity() < seat){
			out.print("false,"+showTimings.getCapacity());
			return;
		}
		
		showTimings.setCapacity(showTimings.getCapacity() - seat);

		Ticket ticket = new Ticket();
		ticket.setAudino(showTimings.getAudi().getAudiid());
		ticket.setMovie(movieDAO.getMovieById(movieid));
		ticket.setCustomer(customer);
		ticket.setCost(cost);
		ticket.setDate(showTimings.getDate());
		ticket.setNoOfSeats(seat);
		ticket.setShowTimings(showTimings);

		ticketDAO.create(ticket);

		out.print("true");

	}


}