package com.Telegraph.services;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Telegraph.dto.UserDto;
import com.Telegraph.models.User;
import com.Telegraph.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	@Autowired
	ModelMapper mapper;
	
	public UserDto findByUsernameAndPassAndRemovedFalse(UserDto userDto) {
		
		User user = repo.findByUsernameAndPassAndRemovedFalse(userDto.getUsername(),userDto.getPass());
		
		if(user == null)
			return null;
		
		return mapper.map(user,UserDto.class);
	}

    public Object findByEmail(String email) {
		User user = repo.findByEmail(email);
		
		if(user == null)
			return null;
		
		return mapper.map(user,UserDto.class);
    }

	public Object findByUsername(String username) {
		User user = repo.findByUsername(username);
		
		if(user == null)
			return null;
		
		return mapper.map(user,UserDto.class);
	}

    public UserDto insert(UserDto userDto) {
		User user = mapper.map(userDto, User.class);
		user.setRemoved(false);
		user.setImgcode(user.getUsername());
		
		User newUser;
		try {
			newUser =repo.save(user);
		} catch (Exception e) {
			return null;
		}

		if(newUser.getId() == null)
			return null;

		return mapper.map(newUser, UserDto.class);
    }

    public UserDto updateImgcode(UserDto userDto,String imgcode) {
        User user = repo.findByUsername(userDto.getUsername());

		user.setImgcode(imgcode);

		User newUser;
		try {
			newUser = repo.save(user);
		} catch (Exception e) {
			return null;
		}

		if(newUser.getImgcode() != imgcode)
			return null;

		return mapper.map(newUser, UserDto.class);
    }

}
