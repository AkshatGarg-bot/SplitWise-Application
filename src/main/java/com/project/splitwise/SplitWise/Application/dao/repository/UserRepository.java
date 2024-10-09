package com.project.splitwise.SplitWise.Application.dao.repository;

import com.project.splitwise.SplitWise.Application.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long id);
}
