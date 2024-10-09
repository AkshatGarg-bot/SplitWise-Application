package com.project.splitwise.SplitWise.Application.controller;

import com.project.splitwise.SplitWise.Application.dao.entity.User;
import com.project.splitwise.SplitWise.Application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user/")
public class UserController {

    @Autowired
    private UserService userService;

    //Adding a user to db
    @PostMapping
    public User saveUser(@RequestBody  User user){
        return userService.saveUser(user);
    }



}
