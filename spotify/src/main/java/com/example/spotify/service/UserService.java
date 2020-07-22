package com.example.spotify.service;

import com.example.spotify.model.User;
import com.example.spotify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> create(User user){
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    public ResponseEntity<?> update(User user){
        userRepository.save(user);
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> view(){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    public ResponseEntity<?> viewById(Integer id){
        User user = userRepository.findById(id).get();
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }
}
