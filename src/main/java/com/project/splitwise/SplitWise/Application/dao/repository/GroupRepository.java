package com.project.splitwise.SplitWise.Application.dao.repository;

import com.project.splitwise.SplitWise.Application.dao.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Long> {
    Optional<Group> findById(long id);
}
