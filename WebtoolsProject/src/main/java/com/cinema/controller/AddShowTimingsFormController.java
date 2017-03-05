package com.cinema.controller;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinema.dao.AuditoriumDAO;
import com.cinema.dao.MovieDAO;
import com.cinema.dao.ShowTimingsDAO;
import com.cinema.dao.TheatreDAO;
import com.cinema.exception.Excep;
import com.cinema.pojo.Auditorium;
import com.cinema.pojo.Movie;
import com.cinema.pojo.ShowTimings;
import com.cinema.pojo.Theatre;


@Controller
@RequestMapping("/addshowtimings.htm")
public class AddShowTimingsFormController {


	@Autowired
	@Qualifier("showTimingsValidator")
	ShowTimingsValidator validator;
	
	@Autowired
	MovieDAO movieDAO;
	
	@Autowired
	ShowTimingsDAO showTimingsDAO;
	
	@Autowired
	TheatreDAO theatreDAO;
	
	@Autowired
	AuditoriumDAO auditoriumDAO;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("showtiming") ShowTimings showTimings, BindingResult result,HttpServletRequest request) throws Exception {
		validator.validate(showTimings, result);
		HttpSession session = request.getSession();
		if (result.hasErrors()) {
			return "addshowtimings";
		}
		
		Movie m = movieDAO.getMovieById(showTimings.getMovieid());
		Auditorium a = auditoriumDAO.get(showTimings.getAudiid());
		Theatre t = theatreDAO.get(showTimings.getTheatreid());
		
		if(m.getTheatres().size()> 0){
			m.getTheatres().add(t);
		}else{
			Set<Theatre> theatreList = new HashSet<Theatre>();
			theatreList.add(t);
			m.setTheatres(theatreList);
		}
		
		movieDAO.create(m);
		showTimings.setAudi(a);
		showTimings.setMovie(m);
		showTimings.setTheatre(t);

		//Setting end time for the movie
		
		@SuppressWarnings("deprecation")
		int min = showTimings.getDate().getMinutes();
		min = min + showTimings.getMovie().getLength();
		
		//System.out.println("Length" + showTimings.getMovie().getLength());
		
		Date d1 = new Date();
		d1  = (Date) showTimings.getDate().clone();
		d1.setMinutes(min);
		
		//System.out.println("Added Date" + d1);
		showTimings.setEndDate(d1);
		
		showTimingsDAO.create(showTimings);
		
		session.setAttribute("message","ShowTiming successfully Updated");
		return "contents";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("showtiming") ShowTimings showTimings, BindingResult result) {

		return "addshowtimings";
	}

	@ModelAttribute
	public void moviesList(Model model){

		try {
			model.addAttribute("movieList", movieDAO.getMoviesExcept("Coming Soon"));
		} catch (Excep e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@ModelAttribute
	public void theatresList(Model model){

		try {
			model.addAttribute("theatresList", theatreDAO.list());
		} catch (Excep e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}