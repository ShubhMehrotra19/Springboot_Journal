package com.journal.journalApp.service;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;


@Component
public class JournalEntryService{
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getEntries(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry getEntryById(ObjectId id){
        return journalEntryRepository.findById(id).isPresent()? journalEntryRepository.findById(id).get() : null;
    }

    public void deleteEntry(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}


// controller call karega ---> service call karega ---> repository
