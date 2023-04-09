package com.Telegraph.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Telegraph.dto.UserDto;
import com.Telegraph.models.Response;
import com.Telegraph.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Telegraph/user")
@CrossOrigin(origins = "http:localhost:8080",allowCredentials = "true")
public class UserController {
    
    @Autowired
	UserService service;

    @PutMapping("/change-pic")
    public Response changePic(@RequestBody UserDto data, HttpServletRequest request, HttpServletResponse response){
		UserDto logUser = (UserDto) request.getSession().getAttribute("logUser");

		if(logUser == null)
			return new Response("invalid session");
		
        if(data == null)
            return new Response("ko");
        
        UserDto userDto = service.updateImgcode(logUser,data.getImgcode());

        if(userDto == null)
            new Response("ko");

        UserDto temp = new UserDto();

        request.getSession().setAttribute("logUser", userDto);

        temp.setImgcode(userDto.getImgcode());

        return new Response("ok", temp);
	}
}
