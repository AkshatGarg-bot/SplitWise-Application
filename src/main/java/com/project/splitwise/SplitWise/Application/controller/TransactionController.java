package com.project.splitwise.SplitWise.Application.controller;

import com.project.splitwise.SplitWise.Application.dao.entity.Transaction;
import com.project.splitwise.SplitWise.Application.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/transaction/")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //Adding a transaction to db
    @PostMapping
    public Transaction saveTransaction(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);
    }

    @DeleteMapping("/{transactionID}")
    public Transaction deleteTransaction(@PathVariable long transactionID){
        return transactionService.deleteTransaction(transactionID);
    }

    @GetMapping("/{transactionID}")
    public Optional<Transaction> getTransaction(@PathVariable long transactionID){
        return transactionService.getTransaction(transactionID);
    }

    @PutMapping()
    public Transaction updateTransaction(@RequestBody Transaction transaction){
        return transactionService.updateTransaction(transaction);
    }
}
