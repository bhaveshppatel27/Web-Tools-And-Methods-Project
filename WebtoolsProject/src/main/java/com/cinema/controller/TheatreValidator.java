package com.cinema.controller;



import java.util.ArrayList;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cinema.pojo.Customer;
import com.cinema.pojo.Movie;
import com.cinema.pojo.Theatre;

import org.springframework.validation.ValidationUtils;


public class TheatreValidator implements Validator {


	
    public boolean supports(Class aClass)
    {
        return aClass.equals(Theatre.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Theatre theatre = (Theatre) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.name", "Theatre Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "locate", "error.invalid.locate", "Location Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "noOfAudi", "error.invalid.noOfAudi", "No. Of Auditorim Required");
    }
}
