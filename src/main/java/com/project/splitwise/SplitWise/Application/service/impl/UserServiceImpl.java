package com.project.splitwise.SplitWise.Application.service.impl;

import com.project.splitwise.SplitWise.Application.dao.entity.User;
import com.project.splitwise.SplitWise.Application.dao.repository.UserRepository;
import com.project.splitwise.SplitWise.Application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        long id = user.getId();
        Optional<User> u = userRepository.findById(id);
        if(u.isEmpty()==false){
            u.get().setName(user.getName());
            u.get().setPhn(user.getPhn());
            return saveUser(u.get());
        }
        return null;
    }

    @Override
    public User deleteUser(User user) {
        userRepository.delete(user);  //make sure to delete the user from the groups he is a part of
        return user;
    }

    @Override
    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }
}
