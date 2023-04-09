package com.Telegraph.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Telegraph.dto.UserDto;
import com.Telegraph.models.Response;
import com.Telegraph.services.UserService;
import com.Telegraph.utils.Checker;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Telegraph")
@CrossOrigin(origins = "http:localhost:8080",allowCredentials = "true")
public class LoginController {
	
	@Autowired
	UserService service;
	
	@PostMapping("/login")
	public Response login(@RequestBody UserDto user, HttpServletRequest request, HttpServletResponse response){
		UserDto loginUser = service.findByUsernameAndPassAndRemovedFalse(user);
		if(loginUser == null)
			return new Response("ko");
		
		request.getSession().setAttribute("logUser", loginUser);
		
		return new Response("ok");
	}

	@PostMapping("/signUp")
	public Response signUp(@RequestBody UserDto user, HttpServletRequest request, HttpServletResponse response){
		
		Response checkData = checkUserData(user);
		if(checkData!= null)
			return checkData;

		
		UserDto loginUser = service.insert(user);
		
		if(loginUser == null)
			return new Response("ko");

		request.getSession().setAttribute("logUser", loginUser);

		return new Response("ok");
	}

	@GetMapping("/session")
	public Response checkSession(HttpServletRequest request){
		UserDto logUser = (UserDto) request.getSession().getAttribute("logUser");

		if(logUser == null)
			return new Response("invalid session");
		
		UserDto userData = new UserDto();

		userData.setUsername(logUser.getUsername());
		userData.setImgcode(logUser.getImgcode());
		userData.setEmail(logUser.getEmail());
		
		return new Response("ok",userData);

	}

	private Response checkUserData(UserDto user){
		
		// user check
		if(user == null)
			return new Response("ko");

		// email check
		String email = user.getEmail();

		if(email == null)
			return new Response("ko","invalid Email");
		
		user.setEmail(email.toLowerCase());
		email = user.getEmail();

		if(!Checker.validateEmail(email))
			return new Response("ko","invalid Email");
			
		if(service.findByEmail(email) != null)
			return new Response("ko","duplicate Email");
		
		// username check
		String username = user.getUsername();

		if(username == null || !Checker.validateUserData(username) )
			return new Response("ko","invalid Username");

		if(service.findByUsername(username) != null)
			return new Response("ko","duplicate Username");

		// username check
		String pass = user.getPass();
		if(pass == null || !Checker.validateUserData(pass) )
			return new Response("ko","invalid Password");

		return null;
	}


	private void createUserCookie(UserDto user,HttpServletResponse response){
		
		//username cokie
		Cookie usernameCookie = new Cookie("usernameCookie", user.getUsername());
		usernameCookie.setPath("/");
		response.addCookie(usernameCookie);

		//
		Cookie imgcodeCookie = new Cookie("usernameCookie", user.getImgcode());
		imgcodeCookie.setPath("/");
		response.addCookie(imgcodeCookie);
	}
}
