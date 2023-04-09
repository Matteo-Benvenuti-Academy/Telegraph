package com.Telegraph.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Telegraph.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsernameAndPassAndRemovedFalse(String username,String pass);

    public User findByUsername(String username);

    public User findByEmail(String email);

}
