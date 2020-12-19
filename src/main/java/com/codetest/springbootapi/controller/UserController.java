package com.codetest.springbootapi.controller;

import com.codetest.springbootapi.entity.UserRepository;
import com.codetest.springbootapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String getUser(@RequestBody User user) {
        userRepository.save(user);
        return "user saved";
    }
}
