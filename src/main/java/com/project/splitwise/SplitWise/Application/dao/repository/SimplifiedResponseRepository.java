package com.project.splitwise.SplitWise.Application.dao.repository;

import com.project.splitwise.SplitWise.Application.dao.entity.Group;
import com.project.splitwise.SplitWise.Application.dao.entity.SimplifiedResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimplifiedResponseRepository extends JpaRepository<SimplifiedResponse , Long> {

    Optional<SimplifiedResponse> findById(long id);
    Optional<SimplifiedResponse> findByGroup(Group group);

}
