package com.cinema.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.dao.MovieDAO;
import com.cinema.pojo.Movie;


@Controller

public class MovieCategoryDisplayController{

	@Autowired
	MovieDAO movieDAO;
	
	
	@RequestMapping(value = "/viewdetail.htm" , method=RequestMethod.GET)
	protected ModelAndView viewDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		ModelAndView mv = new ModelAndView();
		PrintWriter out = response.getWriter();
		
		if(session.getAttribute("customer") == null){
			mv.setViewName("login");
			return mv;
		}
		
		int movieid = Integer.parseInt(request.getParameter("movieid"));

		Movie movie = movieDAO.getMovieById(movieid);
		mv.addObject("movie", movie);
		mv.setViewName("viewmoviedetails");

		//ArrayList<Movie> movieList = (ArrayList<Movie>) movieDAO.getMoviesByCategoryAndPage(category, showMoreCount);


		return mv;

	}

	@RequestMapping(value = "/displaycategory.htm" , method=RequestMethod.GET)
	protected void theatrechange(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		session.setAttribute("count",0);
		PrintWriter out = response.getWriter();

		String category = request.getParameter("category");

		ArrayList<Movie> movieList = (ArrayList<Movie>) movieDAO.getMoviesByCategoryAndPage(category, 0);

		System.out.println(movieList.size());
		for(Movie movie : movieList){
			out.print("<div class = 'col-lg-3 col-md-3 col-sm-3' class ='rowformat'><img src='"+movie.getPhotoName()+"' alt='5' />");
			out.print("<div class='row'>");
			out.print("<div class='col-lg-7' style='overflow: hidden;padding-left:13px;padding-right:0px'><h4 class='font'>"+movie.getTitle()+"</h4></div>");
			out.print("<div class='col-lg-5' style='overflow: hidden;padding-left:13px;padding-right:0px'><h4 class='font'>"+movie.getLength()+" mins</h4></div>");
			out.print("</div>");
			out.print("<div class='row'>");
			out.print("<div class='col-lg-4' style='overflow: hidden;padding-left:13px;padding-right:0px'><h4 class='font'>"+movie.getGenre().getName()+"</h4></div>");
			out.print("<div class='col-lg-8'><button class='button' onclick=\"viewDetail(\'"+movie.getMovieid()+"\')\"><span class='detail'> View Details </span><span class='glyphicon glyphicon-music'></span></button></div>");
			out.print("</div>");
			out.print("</div>");
		}
		out.print("<div class='more' id='showmore'><a href='javascript:void(0)' onclick=\"viewMore(\'"+category+"\')\">View More..</a></div>");
	}

	@RequestMapping(value = "/showmore.htm" , method=RequestMethod.GET)
	protected void showMore(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		int showMoreCount = (Integer) session.getAttribute("count");
		showMoreCount += 8;
		session.setAttribute("count",showMoreCount);
		PrintWriter out = response.getWriter();

		String category = request.getParameter("category");

		ArrayList<Movie> movieList = (ArrayList<Movie>) movieDAO.getMoviesByCategoryAndPage(category, showMoreCount);

		for(Movie movie : movieList){
			out.print("<div class = 'col-lg-3 col-md-3 col-sm-3' class ='rowformat'><img src='"+movie.getPhotoName()+"' alt='5' />");
			out.print("<div class='row'>");
			out.print("<div class='col-lg-7' style='overflow: hidden;padding-left:13px;padding-right:0px'><h4 class='font'>"+movie.getTitle()+"</h4></div>");
			out.print("<div class='col-lg-5' style='overflow: hidden;padding-left:13px;padding-right:0px'><h4 class='font'>"+movie.getLength()+" mins</h4></div>");
			out.print("</div>");
			out.print("<div class='row'>");
			out.print("<div class='col-lg-4' style='overflow: hidden;padding-left:13px;padding-right:0px'><h4 class='font'>"+movie.getGenre().getName()+"</h4></div>");
			out.print("<div class='col-lg-8'><button class='button' onclick=\"viewDetail(\'"+movie.getMovieid()+"\')\"><span class='detail'> View Details </span><span class='glyphicon glyphicon-music'></span></button></div>");
			out.print("</div>");
			out.print("</div>");
		}
		//if(movieList.size() == 4){
		out.print("<div class='more' id='showmore'><a href='javascript:void(0)' onclick=\"viewMore(\'"+category+"\')\">View More..</a></div>");
		//}
	}

	@RequestMapping(value = "/searchmoviesbyname.htm" , method=RequestMethod.GET)
	protected void searchMoviesByname(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		session.setAttribute("count",0);
		PrintWriter out = response.getWriter();

		String moviename = request.getParameter("moviename");

		ArrayList<Movie> movieList = (ArrayList<Movie>) movieDAO.getAllMoviesByName(moviename, 0);

		if(movieList != null){

			for(Movie movie : movieList){
				out.print("<div class = 'col-lg-3 col-md-3 col-sm-3' class ='rowformat'><img src='"+movie.getPhotoName()+"' alt='5' />");
				out.print("<div class='row'>");
				out.print("<div class='col-lg-7' style='overflow: hidden;padding-left:13px;padding-right:0px'><h4 class='font'>"+movie.getTitle()+"</h4></div>");
				out.print("<div class='col-lg-5' style='overflow: hidden;padding-left:13px;padding-right:0px'><h4 class='font'>"+movie.getLength()+" mins</h4></div>");
				out.print("</div>");
				out.print("<div class='row'>");
				out.print("<div class='col-lg-4' style='overflow: hidden;padding-left:13px;padding-right:0px'><h4 class='font'>"+movie.getGenre().getName()+"</h4></div>");
				out.print("<div class='col-lg-8'><button class='button' onclick=\"viewDetail(\'"+movie.getMovieid()+"\')\"><span class='detail'> View Details </span><span class='glyphicon glyphicon-music'></span></button></div>");
				out.print("</div>");
				out.print("</div>");
			}
		}
		//out.print("<div class='more' id='showmore'><a href='javascript:void(0)' onclick=\"viewMore(\'"+category+"\')\">View More..</a></div>");
	}





}