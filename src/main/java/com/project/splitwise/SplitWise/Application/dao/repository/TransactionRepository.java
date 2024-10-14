package com.project.splitwise.SplitWise.Application.dao.repository;

import com.project.splitwise.SplitWise.Application.dao.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Optional<Transaction> findById(long id);
}
