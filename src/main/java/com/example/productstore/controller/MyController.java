package com.example.productstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.productstore.domain.Purchases;
import com.example.productstore.domain.Registration;
import com.example.productstore.dto.RegistrationFormModel;
import com.example.productstore.service.RegistrationService;
import com.example.productstore.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MyController {
	
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private UserService userService;
		
	String logginEmail;
	List<Purchases> myPurchases;
	
	@GetMapping("/register")
	public String registerHere(Model model) {
		model.addAttribute("registerTemplate", new RegistrationFormModel());
		return "registration";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registerTemplate") RegistrationFormModel registerTemplate, BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/register";
		}else {
			Registration user=new Registration();
			user.setEmail(registerTemplate.getEmail());
			user.setUsername(registerTemplate.getUsername());
			user.setPassword(registerTemplate.getPassword());
			registrationService.addDetails(user);
			return "redirect:/";
		
		}
	}   
	
	@PostMapping("/login")
	public String loginHere(@RequestParam("email")String email, @RequestParam("password") String password) {
		if(registrationService.findUser(email, password)) {
			if(email.equals("admin@admin"))
				return "redirect:/adminhome";
			else {
				logginEmail=email;
				return "redirect:/userhome";
			}
		}else {
			
			return "redirect:/?error=true";
		}
	}
	
	@GetMapping("userhome/mypurchases")
	public String purchases(Model model) {
		myPurchases=userService.findMyPurchases(logginEmail);
	    model.addAttribute("purchases", myPurchases);
	    return "mypurchases";
	}

	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
	public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate(); 
	    }
	    return "redirect:/";
	}
 

}
