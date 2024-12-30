package com.project.splitwise.SplitWise.Application.controller;

import com.project.splitwise.SplitWise.Application.dao.entity.Group;
import com.project.splitwise.SplitWise.Application.dao.entity.User;
import com.project.splitwise.SplitWise.Application.service.GroupService;
import com.project.splitwise.SplitWise.Application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user/")
public class UserController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    //Adding a user to db
    @PostMapping
    public User saveUser(@RequestBody  User user){
        return  userService.saveUser(user);

    }

    @DeleteMapping("/{userID}")
    public User deleteUser(@PathVariable long userID){
        return userService.deleteUser(userID);
    }

    @GetMapping("/{userID}")
    public Optional<User> getUser(@PathVariable long userID){
        return userService.getUser(userID);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }



}
