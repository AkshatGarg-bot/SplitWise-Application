package com.project.splitwise.SplitWise.Application.service;

import com.project.splitwise.SplitWise.Application.dao.entity.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    User deleteUser(long id);
    Optional<User> getUser(long id);
}
