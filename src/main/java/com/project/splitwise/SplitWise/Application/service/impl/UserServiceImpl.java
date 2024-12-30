package com.project.splitwise.SplitWise.Application.service.impl;

import com.project.splitwise.SplitWise.Application.dao.entity.Group;
import com.project.splitwise.SplitWise.Application.dao.entity.User;
import com.project.splitwise.SplitWise.Application.dao.repository.UserRepository;
import com.project.splitwise.SplitWise.Application.service.GroupService;
import com.project.splitwise.SplitWise.Application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private GroupService groupService;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        User usr = null;
        List<Long> ids= user.getGroupsIds();
        for(long id:ids)
        {
            Optional<Group> grp = groupService.getGroup(id);
            if(grp.isEmpty())
            {
                return null;
            }
            else
            {
                List<Long> users= grp.get().getUsersIds();
                if(users == null)
                {
                    List<Long> usrs = new ArrayList<>();
                    usrs.add(id);
                    grp.get().setUsersIds(usrs);
                    groupService.saveGroup(grp.get());
                }
                else if(!users.contains(user.getId()))
                {
                    users.add(user.getId());
                    grp.get().setUsersIds(users);
                    groupService.saveGroup(grp.get());
                }
            }
             usr = userRepository.save(user);
        }
        return usr;
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
    public User deleteUser(long id) {
        Optional<User> u = getUser(id);
        if(u.isEmpty()==false){
            userRepository.delete(u.get());
            return u.get();
        }
        //make sure to delete the user from the groups he is a part of
        return null;
    }

    @Override
    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }
}
