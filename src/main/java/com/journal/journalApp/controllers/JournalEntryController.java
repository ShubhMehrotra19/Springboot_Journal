package com.journal.journalApp.controllers;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/create-entry")
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
        return journalEntry;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntry(@PathVariable ObjectId myId){
        return journalEntryService.getEntryById(myId);
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
