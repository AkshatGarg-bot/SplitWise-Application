package com.project.splitwise.SplitWise.Application.controller;

import com.project.splitwise.SplitWise.Application.dao.entity.Group;
import com.project.splitwise.SplitWise.Application.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/group/")
public class GroupController {

    @Autowired
    private GroupService groupService;

    //Adding a group to db
    @PostMapping
    public Group saveGroup(@RequestBody Group group){
        return groupService.saveGroup(group);
    }

    @DeleteMapping("/{groupID}")
    public Group deleteGroup(@PathVariable long groupID){
        return groupService.deleteGroup(groupID);
    }

    @GetMapping("/{groupID}")
    public Optional<Group> getGroup(@PathVariable long groupID){
        return groupService.getGroup(groupID);
    }

    @PutMapping()
    public Group updateGroup(@RequestBody Group group){
        return groupService.updateGroup(group);
    }
}
