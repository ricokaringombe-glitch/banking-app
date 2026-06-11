package com.banking.bankingapp.service;

import com.banking.bankingapp.model.Transaction;
import com.banking.bankingapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction recordTransaction(Long accountId, String type, 
                                        BigDecimal amount, Long fromId, Long toId) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setTransactionType(type);
        transaction.setAmount(amount);
        transaction.setFromAccountId(fromId);
        transaction.setToAccountId(toId);
        transaction.setCreatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
