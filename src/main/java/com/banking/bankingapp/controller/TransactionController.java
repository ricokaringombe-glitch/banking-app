package com.banking.bankingapp.controller;

import com.banking.bankingapp.model.Transaction;
import com.banking.bankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountId}")
    public List<Transaction> getTransactions(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }
}