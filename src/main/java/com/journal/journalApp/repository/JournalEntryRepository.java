package com.journal.journalApp.repository;

import com.journal.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    // MongoRepository is an interface jisme MongoDB ke functions pehle se stored hai. isko OOPs se extend karne se
    // using the mongoDB functions much better.
}
