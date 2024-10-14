package com.project.splitwise.SplitWise.Application.service.impl;

import com.project.splitwise.SplitWise.Application.dao.entity.Transaction;
import com.project.splitwise.SplitWise.Application.dao.repository.TransactionRepository;
import com.project.splitwise.SplitWise.Application.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        long id = transaction.getId();
        Optional<Transaction> t = transactionRepository.findById(id);
        if(t.isEmpty()==false){
            t.get().setAmount(t.get().getAmount());
            t.get().setFromUser(t.get().getFromUser());
            t.get().setToUser(t.get().getToUser());
            return saveTransaction(t.get());
        }
        return null;
    }

    @Override
    public Transaction deleteTransaction(long id) {
        Optional<Transaction> t = getTransaction(id);
        if(t.isEmpty()==false){
            transactionRepository.delete(t.get());
            return t.get();
        }
        //update tranctions in group
        return null;
    }

    @Override
    public Optional<Transaction> getTransaction(long id) {
        return transactionRepository.findById(id);
    }
}
