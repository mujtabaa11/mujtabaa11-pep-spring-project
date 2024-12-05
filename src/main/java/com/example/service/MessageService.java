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

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer messageId) {
        return messageRepository.findById(messageId);
    }

    public boolean deleteMessage(Integer messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return true;
        }
        return false;
    }

    public boolean updateMessage(Integer messageId, String newMessageText) {
        Optional<Message> existingMessage = messageRepository.findById(messageId);

        if (existingMessage.isPresent() && !newMessageText.isBlank() && newMessageText.length() <= 255) {

            Message message = existingMessage.get();
            message.setMessageText(newMessageText);
            messageRepository.save(message);

            return true;
        }
        return false;
    }

    public List<Message> getMessagesByAccount(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}
