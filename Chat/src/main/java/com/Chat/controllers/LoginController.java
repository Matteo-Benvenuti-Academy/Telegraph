package com.Chat.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Chat.dto.UserDto;
import com.Chat.models.Response;
import com.Chat.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/chat/login")
@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	UserService service;
	
	@PostMapping
	public Response login(@RequestBody UserDto user, HttpServletRequest request, HttpServletResponse response){
		UserDto loginUser = service.findByUsernameAndPass(user);
		if(loginUser == null)
			return new Response("ko");
		
		return new Response("ok",loginUser);
	}
}
