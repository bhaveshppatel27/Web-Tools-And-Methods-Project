package com.cinema.controller;



import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinema.dao.ActorDAO;
import com.cinema.dao.ActressDAO;
import com.cinema.dao.CustomerDAO;
import com.cinema.dao.DirectorDAO;
import com.cinema.dao.GenreDAO;
import com.cinema.dao.MovieDAO;
import com.cinema.exception.Excep;
import com.cinema.pojo.Actor;
import com.cinema.pojo.Actress;
import com.cinema.pojo.Customer;
import com.cinema.pojo.Director;
import com.cinema.pojo.Genre;
import com.cinema.pojo.Movie;



@Controller
@RequestMapping("/addmovie.htm")
public class AddMovieFormController {


	@Autowired
	@Qualifier("movieValidator")
	MovieValidator validator;

	@Autowired
	ServletContext servletContext;

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

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);


	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("movie") Movie movie, BindingResult result, HttpServletRequest request) throws Exception {
		validator.validate(movie, result);
		HttpSession session = request.getSession();
		try {


			File file;
			String check = File.separator; //Checking if system is linux based or windows based by checking seprator used.
			String path = null;
			if(check.equalsIgnoreCase("\\")) {
				path = servletContext.getRealPath("");
				int len = path.length();
				path= path.substring(0, len-1);
				path = (path +"\\car\\");
				//Netbeans projects gives real path as Lab6/build/web/ so we need to replace build in the path.
			}

			if(check.equalsIgnoreCase("/")) {
				path = servletContext.getRealPath("");
				path += "/"; //Adding trailing slash for Mac systems.

			}

			if(movie.getPhoto() != null){
				String fileNAmeWithExt = System.currentTimeMillis() + movie.getPhoto().getOriginalFilename();
				file = new File(path + fileNAmeWithExt);
				String context = servletContext.getContextPath();

				movie.getPhoto().transferTo(file);
				movie.setPhotoName(context + "/car/" + fileNAmeWithExt);

			}


			//genre
			int genre = movie.getGen();

			Genre g = genreDAO.get(genre);
			movie.setGenre(g);


			//actors
			ArrayList<Integer> act = movie.getAct();

			Set<Actor> actors = new HashSet<Actor>();
			for(int i= 0;i<act.size();i++){
				Actor a = actorDAO.get(act.get(i));
				actors.add(a);
			}
			movie.setActors(actors);
			//movieDAO.create(movie);

			//actress
			ArrayList<Integer> actr = movie.getActr();

			Set<Actress> actress = new HashSet<Actress>();
			for(int i= 0;i<actr.size();i++){
				Actress a = actressDAO.get(actr.get(i));
				actress.add(a);
			}
			movie.setActress(actress);
			//movieDAO.create(movie);

			//actress
			ArrayList<Integer> dir = movie.getDir();

			Set<Director> directors = new HashSet<Director>();
			for(int i= 0;i<dir.size();i++){
				Director d = directorDAO.get(dir.get(i));
				directors.add(d);
			}
			movie.setDirectors(directors);

			movieDAO.create(movie);


		} catch (Excep e) {

			System.out.print("Error executing hibernate" + e);
			return "addmovie";

		}
		session.setAttribute("message", movie.getTitle()+" successfully added");
		return "contents";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("movie") Movie movie, BindingResult result) {

		return "addmovie";
	}

	@ModelAttribute
	public void genreList(Model model){

		try {
			model.addAttribute("genreList", genreDAO.list());
		} catch (Excep e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ModelAttribute
	public void actorsList(Model model){

		try {
			model.addAttribute("actorsList", actorDAO.list());
		} catch (Excep e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ModelAttribute
	public void actressList(Model model){

		try {
			model.addAttribute("actressList", actressDAO.list());
		} catch (Excep e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ModelAttribute
	public void directorsList(Model model){

		try {
			model.addAttribute("directorsList", directorDAO.list());
		} catch (Excep e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}