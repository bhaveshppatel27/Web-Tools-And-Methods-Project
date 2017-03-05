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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.cinema.Addons.PdfTicketView;
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
public class ViewTicketController{

	@Autowired
	CustomerDAO customerDAO;

	@RequestMapping(value = "/viewticket.htm" , method=RequestMethod.GET)
	protected String viewTicket(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		PrintWriter out = response.getWriter();

		Customer customer = (Customer) session.getAttribute("customer");

		if(customer == null){
			return "login";
		}
		Customer customer1 = customerDAO.getById(customer.getEmail());
		session.setAttribute("customer", customer1);
		session.setAttribute("ticketsList", customer1.getTickets());
		return "viewticket";

	}

	@RequestMapping(value = "/ticketpdf.htm", method = RequestMethod.GET)
	public ModelAndView createReport()
	{
		View view = new PdfTicketView();

		return new ModelAndView(view);
	}



}