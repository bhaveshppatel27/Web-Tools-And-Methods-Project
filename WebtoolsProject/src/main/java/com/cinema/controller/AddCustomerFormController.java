package com.cinema.controller;



import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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
import com.cinema.Addons.RandomNumberGenerator;
import com.cinema.dao.BankAccountDAO;
import com.cinema.dao.CustomerDAO;
import com.cinema.exception.Excep;
import com.cinema.pojo.BankAccount;
import com.cinema.pojo.Customer;


@Controller
@RequestMapping("/addcustomer.htm")
public class AddCustomerFormController {

	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	BankAccountDAO bankAccountDAO;
	
	@Autowired
	@Qualifier("customerValidator")
	CustomerValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("customer") Customer customer, BindingResult result,HttpServletRequest request) throws Exception {
		validator.validate(customer, result);
		if (result.hasErrors()) {
			return "addCustomerForm";
		}

		try {

	    	   Customer c = customerDAO.get(customer.getUsername());
			
			if(c != null){

				return "addCustomerForm";
			}
			customerDAO.create(customer);
			

		} catch (Excep e) {

			return "addCustomerForm";
		}
		request.getSession().setAttribute("customer", customer);
		RandomNumberGenerator rng = new RandomNumberGenerator();
		
		
		BankAccount ba = new BankAccount();
		ba.setCardnumber(rng.getCardNumber());
		ba.setPin(rng.getPIN());
		ba.setCustomer(customer);
		bankAccountDAO.create(ba);
		EmailSender.sendEmail(customer);
		
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("customer") Customer customer, BindingResult result) {

		return "addCustomerForm";
	}
	
}