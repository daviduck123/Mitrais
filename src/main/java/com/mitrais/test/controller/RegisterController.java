package com.mitrais.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mitrais.test.model.User;
import com.mitrais.test.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index(Model model) {
		return "register";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value= "/save", method=RequestMethod.POST)
	@ResponseBody
	public void save(@RequestBody Map<String, Object> map) {
		User user = new User();
		user.setFirstName(map.get("firstName").toString());
		user.setLastName(map.get("lastName").toString());
		user.setEmail(map.get("email").toString());
		user.setMobile(map.get("mobile").toString());
		if(map.get("gender") != null) {
			user.setGender(map.get("gender").toString());
		}
		if(map.get("date") != null) {
			try {
				Date date1 = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss.SSS'Z'").parse(map.get("date").toString());  
				user.setDate(date1);
			}catch(Exception e) {
				user.setDate(null);
			}
		}
		userService.saveUser(user);
	}
	
	@RequestMapping("/getUserByEmail")
	@ResponseBody
	public User getUserByEmail(@RequestParam(value = "email") String email) {
		return userService.getUserByEmail(email);
	}
	
	@RequestMapping("/getUserByMobile")
	@ResponseBody
	public User getUserByMobile(@RequestParam(value = "mobile") String mobile) {
		return userService.getUserByMobile(mobile);
	}
}
