package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;

import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping("/api")
public class SocialMediaController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    // ----------------- Account Endpoints -----------------

    /**
     * Endpoint to create a new account.
     * @param account The account data to be created.
     * @return The created account.
     */
    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountService.createAccount(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve an account by its ID.
     * @param accountId The ID of the account to retrieve.
     * @return The account details.
     */
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer accountId) {
        Account account = accountService.getAccountById(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve all accounts.
     * @return A list of all accounts.
     */
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    /**
     * Endpoint to update an account.
     * @param accountId The ID of the account to update.
     * @param account The updated account data.
     * @return The updated account.
     */
    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Integer accountId, @RequestBody Account account) {
        Account updatedAccount = accountService.updateAccount(accountId, account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    /**
     * Endpoint to delete an account.
     * @param accountId The ID of the account to delete.
     * @return HTTP Status indicating success or failure.
     */
    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer accountId) {
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ----------------- Message Endpoints -----------------

    /**
     * Endpoint to post a new message.
     * @param message The message data to be posted.
     * @return The created message.
     */
    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        Message savedMessage = messageService.postMessage(message);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve a message by its ID.
     * @param messageId The ID of the message to retrieve.
     * @return The message with the given ID.
     */
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        Message message = messageService.getMessageById(messageId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve all messages posted by a specific user.
     * @param userId The user ID to filter messages by.
     * @return A list of messages posted by the user.
     */
    @GetMapping("/messages/user/{userId}")
    public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable Integer userId) {
        List<Message> messages = messageService.getMessagesByUser(userId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a message by its ID.
     * @param messageId The ID of the message to delete.
     * @return HTTP Status indicating success or failure.
     */
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Integer messageId) {
        messageService.deleteMessage(messageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
