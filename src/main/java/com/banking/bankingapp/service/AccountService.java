package com.banking.bankingapp.service;

import com.banking.bankingapp.model.Account;
import com.banking.bankingapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    public Account createAccount(Account account) {
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account deposit(Long id, BigDecimal amount) {
        Account account = getAccountById(id);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        transactionService.recordTransaction(id, "DEPOSIT", amount, null, null);
        return account;
    }

    public Account withdraw(Long id, BigDecimal amount) {
        Account account = getAccountById(id);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        transactionService.recordTransaction(id, "WITHDRAW", amount, null, null);
        return account;
    }

    public Account transfer(Long fromId, Long toId, BigDecimal amount) {
        Account fromAccount = getAccountById(fromId);
        Account toAccount = getAccountById(toId);
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        transactionService.recordTransaction(fromId, "TRANSFER", amount, fromId, toId);
        transactionService.recordTransaction(toId, "TRANSFER", amount, fromId, toId);
        return fromAccount;
    }
}