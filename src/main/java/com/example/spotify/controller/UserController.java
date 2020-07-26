package com.example.spotify.controller;

import com.example.spotify.model.User;
import com.example.spotify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "https://silly-pare-01c9f2.netlify.app/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody User user){
        return userService.create(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> viewById(@PathVariable Integer id){
        return userService.viewById(id);
    }

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public ResponseEntity<?> view(){
        return userService.view();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody User user){
        return userService.update(user);
    }

    @RequestMapping(value = "/login/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable String email,@PathVariable String password){
        return userService.login(email,password);
    }

}
