package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    // Create a new message
    public Message createMessage(Message message) {
        return messageRepository.save(message); // Save message to the database
    }

    // Get all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll(); // Retrieve all messages
    }

    // Get a message by its ID
    public Optional<Message> getMessageById(Integer messageId) {
        return messageRepository.findById(messageId); // Find message by ID
    }

    // Delete a message by ID
    public boolean deleteMessage(Integer messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId); // Delete the message
            return true;
        }
        return false; // Return false if the message does not exist
    }

    // Update message text by ID
    public boolean updateMessage(Integer messageId, String newMessageText) {
        Optional<Message> existingMessage = messageRepository.findById(messageId);
        if (existingMessage.isPresent() && !newMessageText.isBlank() && newMessageText.length() <= 255) {
            Message message = existingMessage.get();
            message.setMessageText(newMessageText); // Update the message text
            messageRepository.save(message); // Save updated message
            return true;
        }
        return false; // Return false if message doesn't exist or validation fails
    }

    // Get all messages written by a particular user
    public List<Message> getMessagesByAccount(Integer accountId) {
        return messageRepository.findByPostedBy(accountId); // Retrieve messages for a specific user
    }
}
