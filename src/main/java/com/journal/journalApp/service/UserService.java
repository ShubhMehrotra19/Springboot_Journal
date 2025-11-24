package com.journal.journalApp.service;

import com.journal.journalApp.entity.User;
import com.journal.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getEntries(){
        return userRepository.findAll();
    }

    public User getEntryById(ObjectId id){
        return userRepository.findById(id).isPresent()? userRepository.findById(id).get() : null;
    }

    public void deleteEntry(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}


// controller call karega ---> service call karega ---> repository
