package com.cinema.Addons;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;



@Controller
public class RestController {
	@RequestMapping(value = "/abc.htm", method = RequestMethod.GET)
	public void home(HttpServletRequest request,HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try{
		String urlOfTheRestService = "http://api.wunderground.com/api/f1bebf03ed9d57c1/conditions/q/MA/Boston.json";
		RestTemplate restTemplate = new RestTemplate();
		String restData = restTemplate.getForObject(urlOfTheRestService, String.class);

		JSONObject json = new JSONObject(restData);
		out.print(json);
		}
		catch(Exception e){
			out.print("false");
		}

	}

}
