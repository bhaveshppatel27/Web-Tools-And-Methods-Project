package com.cinema.controller;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cinema.dao.CustomerDAO;
import com.cinema.pojo.Customer;

import org.springframework.validation.ValidationUtils;


public class CustomerValidator implements Validator {


	
    public boolean supports(Class aClass)
    {
        return aClass.equals(Customer.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Customer customer = (Customer) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.firstName", "First Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.lastName", "Last Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.username", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email", "Email Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.invalid.city", "City Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Province", "error.invalid.province", "Province Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.invalid.address", "Address Required");
        
        
        
    }
}
