package com.journal.journalApp.entity;

// This is POJO : Plain Old Java Object

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId id;

    // indexing karne se the username would have to be true now, so hamare liye searching fast ho jayegi
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    // @DBRef se ham reference ko attach kar rahe hai and link kar rahe hai baaki dono
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
