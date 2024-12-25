package com.project.splitwise.SplitWise.Application.service.impl;

import com.project.splitwise.SplitWise.Application.dao.entity.Group;
import com.project.splitwise.SplitWise.Application.dao.repository.GroupRepository;
import com.project.splitwise.SplitWise.Application.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    @Autowired
    private final GroupRepository groupRepository;

    @Override
    public Group saveGroup(Group group) {

        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Group group) {
        long id = group.getId();
        Optional<Group> g = groupRepository.findById(id);
        if(g.isEmpty()==false){
            g.get().setName(group.getName());
            g.get().setTransactionsIds(group.getTransactionsIds());
            g.get().setUsersIds(group.getUsersIds());
            return saveGroup(g.get());
        }
        return null;
    }

    @Override
    public Group deleteGroup(long id) {
        Optional<Group> g = getGroup(id);
        if(g.isEmpty()==false){
            groupRepository.delete(g.get());
            return g.get();
        }
        //make sure to delete the all the transactions from the groups
        return null;
    }

    @Override
    public Optional<Group> getGroup(long id) {
        return groupRepository.findById(id);
    }
}
