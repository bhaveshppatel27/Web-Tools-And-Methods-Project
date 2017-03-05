package com.cinema.controller;



import java.util.ArrayList;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cinema.pojo.Customer;
import com.cinema.pojo.Movie;

import org.springframework.validation.ValidationUtils;


public class MovieValidator implements Validator {


	
    public boolean supports(Class aClass)
    {
        return aClass.equals(Movie.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Movie movie = (Movie) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.title", "Title Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "length", "error.invalid.length", "Length Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "error.invalid.year", "Year Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.description", "Description Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "act", "error.invalid.act", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "actr", "error.invalid.actr", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dir", "error.invalid.dir", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gen", "error.invalid.gen", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photo", "error.invalid.photo", "Image Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "error.invalid.category", "Category Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "budget.makingcost", "error.invalid.budget.makingcost", "Cost Required");
    }
}
