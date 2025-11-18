package com.journal.journalApp.controllers;

import com.journal.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")     // request mapping se poori class pe mapping add ho jaati hai.
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry journalEntry){
        //JournalEntries ka ek instance ban jayega isme.

        journalEntries.put(journalEntry.getId(), journalEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntry(@PathVariable Long myId){
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntry(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }

    @PutMapping
    public JournalEntry updateEntry(@PathVariable Long id ,@RequestBody JournalEntry journalEntry){
        return journalEntries.put(id, journalEntry);
    }
}
