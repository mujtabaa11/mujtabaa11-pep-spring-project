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

    /**
     * Post a new message.
     * @param message The message data to be posted.
     * @return The created message.
     */
    public Message postMessage(Message message) {
        return messageRepository.save(message);
    }

    /**
     * Get a message by its ID.
     * @param messageId The ID of the message.
     * @return The message with the given ID.
     */
    public Message getMessageById(Integer messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        if (message.isPresent()) {
            return message.get();
        } else {
            throw new RuntimeException("Message not found with ID: " + messageId);
        }
    }

    /**
     * Get all messages posted by a specific user.
     * @param userId The ID of the user whose messages to retrieve.
     * @return A list of messages posted by the user.
     */
    public List<Message> getMessagesByUser(Integer userId) {
        return messageRepository.findByPostedBy(userId);
    }

    /**
     * Delete a message by its ID.
     * @param messageId The ID of the message to delete.
     */
    public void deleteMessage(Integer messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        if (message.isPresent()) {
            messageRepository.delete(message.get());
        } else {
            throw new RuntimeException("Message not found with ID: " + messageId);
        }
    }
}
