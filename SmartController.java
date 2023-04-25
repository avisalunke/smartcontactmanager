package com.smartcontactmanager.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartcontactmanager.code.Dao.Dao;
import com.smartcontactmanager.code.Entity.user;
import com.smartcontactmanager.code.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class SmartController {

	@Autowired
	Dao dao;
	
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart-Contact-Manager");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model ) {
		model.addAttribute("title","About - Smart-Contact-Manager");
		return "about";
	}
	
	@RequestMapping("/login")
	public String login(Model model ) {
		model.addAttribute("title","Login - Smart-Contact-Manager");
		return "login";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model ) {
		model.addAttribute("title","Signup - Smart-Contact-Manager");
		model.addAttribute("userr", new user());
		return "signup";
	}
	
	@RequestMapping(value="/do_register",method = RequestMethod.POST)
	public String doregister(@Valid @ModelAttribute("userr") user userr,BindingResult result1,@RequestParam(value = "aggrement",defaultValue = "false") boolean aggrement,Model model,HttpSession session) {
		
		try {
			if(!aggrement) {
				System.out.println("you have not aggred to terms and condition..");
				throw new Exception("you have not agred to terms and condition");
			}
			
			if(result1.hasErrors()) {
				model.addAttribute("user",userr);
				return "signup";
			}
			
			userr.setRole("ROLE_USER");
			userr.setImageurl("default.png");
			System.out.println("aggrement "+aggrement);
			System.out.println("USER"+ userr);
			dao.save(userr);
			session.setAttribute("message", new Message("successfullt register!!", "alert-success"));
			return "signup";
			
		}
		catch (Exception e) {
				model.addAttribute("userr", userr);
				System.out.println("you have not aggred to terms and condition!!!");
			session.setAttribute("message", new Message("something went wrong!!", "alert-danger"));
			return "signup";

		}
		
	
		
		
		
	}
}
