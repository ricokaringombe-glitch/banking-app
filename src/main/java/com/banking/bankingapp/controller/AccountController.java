package com.banking.bankingapp.controller;

import com.banking.bankingapp.model.Account;
import com.banking.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return accountService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return accountService.withdraw(id, amount);
    }
    @PostMapping("/transfer")
public Account transfer(
    @RequestParam Long fromId,
    @RequestParam Long toId,
    @RequestParam BigDecimal amount) {
    return accountService.transfer(fromId, toId, amount);
}

   


}