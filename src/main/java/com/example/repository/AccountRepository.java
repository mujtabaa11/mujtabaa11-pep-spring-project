package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    // Check if an account with the given username exists
    boolean existsByUsername(String username);

    // Find an account by username and password
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
