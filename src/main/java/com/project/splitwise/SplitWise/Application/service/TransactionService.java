package com.project.splitwise.SplitWise.Application.service;

import com.project.splitwise.SplitWise.Application.dao.entity.Transaction;

import java.util.Optional;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction transaction);
    Transaction deleteTransaction(long id);
    Optional<Transaction> getTransaction(long id);
}
