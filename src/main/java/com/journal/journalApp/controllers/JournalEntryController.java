package com.journal.journalApp.controllers;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")     // request mapping se poori class pe mapping add ho jaati hai.
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getEntries();
    }

    // RespnseEntity helps in implementing the HTTP request codes in the codebase and API logs.

    @PostMapping("/create-entry")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry){
        try{
            journalEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(journalEntry);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntry(@PathVariable ObjectId myId){
        JournalEntry journalEntry = journalEntryService.getEntryById(myId);
        if(journalEntry != null){
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public void deleteEntry(@PathVariable ObjectId myId){
        journalEntryService.deleteEntry(myId);
    }

    @PutMapping
    public JournalEntry updateEntry(@PathVariable ObjectId id ,@RequestBody JournalEntry journalEntry){
        JournalEntry old = journalEntryService.getEntryById(id);
        if(old != null){
            old.setDate(LocalDateTime.now());
            old.setTitle(journalEntry.getTitle() != null && journalEntry.getTitle().isEmpty() ? journalEntry.getTitle() : old.getTitle());
            old.setContent(journalEntry.getContent() !=  null && !journalEntry.getContent().isEmpty() ? journalEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}
