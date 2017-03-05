package com.cinema.controller;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cinema.pojo.ShowTimings;

import org.springframework.validation.ValidationUtils;


public class ShowTimingsValidator implements Validator {


	
    public boolean supports(Class aClass)
    {
        return aClass.equals(ShowTimings.class);
    }

    public void validate(Object obj, Errors errors)
    {
    	ShowTimings showTimings = (ShowTimings) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "movieid", "error.invalid.movieid", "Movie Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "theatreid", "error.invalid.theatreid", "Theatre Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "audiid", "error.invalid.audiid", "Auditorium Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capacity", "error.invalid.capacity", "Capacity Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "error.invalid.date", "Incorrect Date");
        
        
        
//        if(showTimings.getDate().before(new Date()) ){
//			errors.rejectValue("date", "date cannot be past");
//		}

    }
}
