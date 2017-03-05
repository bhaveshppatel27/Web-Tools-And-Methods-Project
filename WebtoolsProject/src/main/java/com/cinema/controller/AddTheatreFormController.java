package com.cinema.controller;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinema.Addons.EmailSender;
import com.cinema.dao.AuditoriumDAO;
import com.cinema.dao.CustomerDAO;
import com.cinema.dao.LocationDAO;
import com.cinema.dao.MovieDAO;
import com.cinema.dao.TheatreDAO;
import com.cinema.exception.Excep;
import com.cinema.pojo.Auditorium;
import com.cinema.pojo.Customer;
import com.cinema.pojo.Location;
import com.cinema.pojo.Theatre;


@Controller
@RequestMapping("/addtheatre.htm")
public class AddTheatreFormController {


	@Autowired
	@Qualifier("theatreValidator")
	TheatreValidator validator;

	@Autowired
	TheatreDAO theatreDAO;

	@Autowired
	AuditoriumDAO auditoriumDAO;

	@Autowired
	LocationDAO locationDAO;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("theatre") Theatre theatre, BindingResult result,HttpServletRequest request) throws Exception {
		validator.validate(theatre, result);
		HttpSession session = request.getSession();
		if (result.hasErrors()) {
			return "addtheatre";
		}

		try {

			Theatre t = theatreDAO.getTheatreByName(theatre.getName());
			if(t != null){
				if(t.getName().equalsIgnoreCase(theatre.getName()) && t.getLocation().getLocate().equalsIgnoreCase(theatre.getLocate())){

					return "addtheatre";
				}
			}

			ArrayList<Location> locationList = (ArrayList<Location>) locationDAO.list();
			Location loc = new Location();
			loc.setLocate(theatre.getLocate());
			if(locationList.size() <= 0){
				
				locationDAO.create(loc);
			}else{
				for(Location l : locationList){

					if(l.getLocate().equalsIgnoreCase(theatre.getLocate())){
						loc = l;
						break;
					}				
				}
				
				locationDAO.create(loc);
			}
			theatre.setLocation(loc);
			theatreDAO.create(theatre);
			for(int i =1;i<=theatre.getNoOfAudi();i++){
				Auditorium a = new Auditorium();
				a.setName("A"+i);
				a.setTheatre(theatre);
				auditoriumDAO.create(a);

			}



		} catch (Excep e) {

			return "addtheatre";
		}
		session.setAttribute("message", theatre.getName()+" successfully added");
		return "contents";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("theatre") Theatre theatre, BindingResult result) {

		return "addtheatre";
	}

}