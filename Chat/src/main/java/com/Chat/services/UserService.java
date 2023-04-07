package com.Chat.services;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Chat.dto.UserDto;
import com.Chat.models.User;
import com.Chat.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	@Autowired
	ModelMapper mapper;
	
	public UserDto findByUsernameAndPass(UserDto userDto) {
		
		User user = repo.findByUsernameAndPass(userDto.getUsername(),userDto.getPass());
		
		if(user == null)
			return null;
		
		return mapper.map(user,UserDto.class);
	}

}
