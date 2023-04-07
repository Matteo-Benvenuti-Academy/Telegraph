package com.Chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Chat.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsernameAndPass(String username,String pass);

}
