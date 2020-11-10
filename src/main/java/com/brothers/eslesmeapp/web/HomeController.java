package com.brothers.eslesmeapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
	@RequestMapping("/home")
	public String home() {
		return "home";
		
	}
	
	  @GetMapping("/")
	    String greetingPage(ModelMap map, @RequestParam(required = false) String name,@RequestParam(required = false) String percentage) {
	        name = name == null || name.trim().equals("") ? "Thymeleaf" : name;
	        percentage = percentage == null || percentage.trim().equals("") ? "Per 0" : percentage;

	        String message = String.format("Hoşgeldiniz %s!", name);
	        String message_per = String.format("percentage %s!", percentage);
	        map.put("message", message);
	        map.put("percentage", message_per);
	        return "index";
	    }
	
}
