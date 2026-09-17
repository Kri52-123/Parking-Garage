package com.parking.builder.service;

import com.parking.builder.model.User;
import com.parking.builder.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repo;
    public AuthService(UserRepository repo){this.repo=repo;}
    public User register(String username,String password){
        if(username==null||username.isBlank()||password==null||password.isBlank()) throw new IllegalArgumentException("Username and password are required");
        if(repo.findByUsername(username).isPresent()) throw new IllegalArgumentException("Username already exists");
        return repo.save(new User(username,password));
    }
    public User login(String username,String password){
        User user=repo.findByUsername(username).orElseThrow(()->new IllegalArgumentException("Invalid username or password"));
        if(!user.getPassword().equals(password)) throw new IllegalArgumentException("Invalid username or password");
        return user;
    }
}
