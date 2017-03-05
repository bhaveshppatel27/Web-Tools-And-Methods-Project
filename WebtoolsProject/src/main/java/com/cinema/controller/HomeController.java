package com.cinema.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.dao.MovieDAO;
import com.cinema.pojo.Movie;



@Controller
@RequestMapping("/home.htm")
public class HomeController{

	@Autowired
	MovieDAO movieDAO;
	
	
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		PrintWriter out = response.getWriter();

		ModelAndView mv = new ModelAndView();

		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("login")){

			mv.setViewName("login");
		}
		else if(action.equalsIgnoreCase("theatreAdminLogin")){
			mv.setViewName("theatreadminlogin");
		}
		else{
			if(action.equalsIgnoreCase("logout")){
				session.invalidate();
			}

			request.getSession().setAttribute("count",0);
			ArrayList<Movie> movieList = (ArrayList<Movie>) movieDAO.getMoviesExcludingCategory("Coming Soon");
			ArrayList<Movie> list = (ArrayList<Movie>) movieDAO.getMoviesByCategoryAndPage("CurrentListings",0);
			mv.addObject("movielist", list);
			request.getSession().setAttribute("movielist", list);
			request.getSession().setAttribute("quickbookmovies", movieList);
			mv.setViewName("home");
		}
		return mv;
	}
}