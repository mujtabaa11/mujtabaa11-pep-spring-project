package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // Register a new account
    public Account registerAccount(Account account) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            return null; // Account with username already exists
        }
        return accountRepository.save(account); // Save new account to the database
    }

    // User login (find by username and password)
    public Optional<Account> login(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password);
    }

    // Get account by ID (used for validation when creating a message)
    public Account getAccountById(Integer accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }
}
