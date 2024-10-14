package com.project.splitwise.SplitWise.Application.service;


import com.project.splitwise.SplitWise.Application.dao.entity.Group;

import java.util.Optional;

public interface GroupService {
    Group saveGroup(Group group);
    Group updateGroup(Group group);
    Group deleteGroup(long id);
    Optional<Group> getGroup(long id);
}
