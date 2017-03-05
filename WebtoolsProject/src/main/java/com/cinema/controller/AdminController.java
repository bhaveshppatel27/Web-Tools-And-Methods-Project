package com.cinema.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.dao.ActorDAO;
import com.cinema.dao.ActressDAO;
import com.cinema.dao.CustomerDAO;
import com.cinema.dao.DirectorDAO;
import com.cinema.dao.GenreDAO;
import com.cinema.dao.MovieDAO;
import com.cinema.pojo.Actor;
import com.cinema.pojo.Actress;
import com.cinema.pojo.Customer;
import com.cinema.pojo.Director;
import com.cinema.pojo.Genre;
import com.cinema.pojo.Movie;
import com.cinema.pojo.TheatreAdmin;


@Controller
@RequestMapping("/adminhome.htm")
public class AdminController{

	@RequestMapping(method=RequestMethod.GET)
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session =request.getSession();
		PrintWriter out = response.getWriter();
		
		Customer c = (Customer) session.getAttribute("customer");
		TheatreAdmin ta = (TheatreAdmin) session.getAttribute("theatreadmin");
		
        ModelAndView mv = new ModelAndView();
        
        if(c == null && ta == null){
			mv.setViewName("login");
			return mv;
		}
        
        String action = request.getParameter("action");
        
        if(action.equalsIgnoreCase("home")){
        	mv.setViewName("adminhome");
        }
        else if(action.equalsIgnoreCase("genre")){
        	mv.setViewName("addgenre");
        }
        else if(action.equalsIgnoreCase("actor")){
        	mv.setViewName("addactor");
        }
        else if(action.equalsIgnoreCase("actress")){
        	mv.setViewName("addactress");
        }
        else if(action.equalsIgnoreCase("director")){
        	mv.setViewName("adddirector");
        }
        return mv;
    }
}