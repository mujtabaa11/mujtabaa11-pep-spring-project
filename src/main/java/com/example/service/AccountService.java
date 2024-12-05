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

    public Account registerAccount(Account account) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            return null;
        }
        return accountRepository.save(account);
    }

    public Optional<Account> login(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password);
    }

    public Account getAccountById(Integer accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }
}
