package com.project.splitwise.SplitWise.Application.service.impl;

import com.project.splitwise.SplitWise.Application.dao.entity.Group;
import com.project.splitwise.SplitWise.Application.dao.entity.Transaction;
import com.project.splitwise.SplitWise.Application.dao.entity.User;
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

    @Autowired
    private final UserServiceImpl userService;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        Long groupsId = transaction.getGroupId();
        Optional<Group> group = groupService.getGroup(groupsId);
        if(!userService.checkUser(transaction)) return null;
        if (group.isEmpty()) {
            return null;
        }
        else {
            //add transaction in group transactionIds list and save transaction in db
            List<Long> transactionList = new ArrayList<>();
            if (group.get().getTransactionsIds() != null) {
                transactionList = group.get().getTransactionsIds();
            }
            transactionList.add(transaction.getId());
            group.get().setTransactionsIds(transactionList);
            groupService.updateGroup(group.get());
            return transactionRepository.save(transaction);
        }
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        long id = transaction.getId();
        Optional<Transaction> t = transactionRepository.findById(id);
        if (t.isEmpty() == false) {
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
        if (t.isEmpty() == false) {
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
