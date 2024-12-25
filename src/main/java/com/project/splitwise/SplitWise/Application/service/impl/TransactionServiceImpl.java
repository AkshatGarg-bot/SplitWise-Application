package com.project.splitwise.SplitWise.Application.service.impl;

import com.project.splitwise.SplitWise.Application.dao.entity.Group;
import com.project.splitwise.SplitWise.Application.dao.entity.Transaction;
import com.project.splitwise.SplitWise.Application.dao.repository.TransactionRepository;
import com.project.splitwise.SplitWise.Application.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    private final GroupServiceImpl groupService;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        //update transaction in group
        Transaction savedtransaction = transactionRepository.save(transaction);
        Group group = groupService.getGroup(transaction.getGroupId()).orElse(null);
        if(group!=null){
            //add transaction in group transactionIds list and save transaction in db
            List<Long> transactionList = new ArrayList<>();
            if(group.getTransactionsIds()!=null){
                transactionList = group.getTransactionsIds();
            }
            transactionList.add(savedtransaction.getId());
            group.setTransactionsIds(transactionList);
            groupService.updateGroup(group);
            return savedtransaction;
        }
        else{
            System.out.println("Group id don't exist");
        }
        return null;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        long id = transaction.getId();
        Optional<Transaction> t = transactionRepository.findById(id);
        if(t.isEmpty()==false){
            t.get().setAmount(t.get().getAmount());
            t.get().setFromUserId(t.get().getFromUserId());
            t.get().setToUserId(t.get().getToUserId());
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
