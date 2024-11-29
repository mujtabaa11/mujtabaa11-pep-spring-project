package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Create a new account.
     * @param account The account data to be created.
     * @return The created account.
     */
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Get an account by its ID.
     * @param accountId The ID of the account.
     * @return The account with the given ID.
     */
    public Account getAccountById(Integer accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RuntimeException("Account not found with ID: " + accountId);
        }
    }

    /**
     * Get all accounts.
     * @return A list of all accounts.
     */
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Update an account by its ID.
     * @param accountId The ID of the account to update.
     * @param updatedAccount The account with updated information.
     * @return The updated account.
     */
    public Account updateAccount(Integer accountId, Account updatedAccount) {
        Optional<Account> existingAccount = accountRepository.findById(accountId);
        if (existingAccount.isPresent()) {
            Account account = existingAccount.get();
            account.setUsername(updatedAccount.getUsername());
            account.setPassword(updatedAccount.getPassword());
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found with ID: " + accountId);
        }
    }

    /**
     * Delete an account by its ID.
     * @param accountId The ID of the account to delete.
     */
    public void deleteAccount(Integer accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            accountRepository.delete(account.get());
        } else {
            throw new RuntimeException("Account not found with ID: " + accountId);
        }
    }
}
