package com.journal.journalApp.entity;

// This is POJO : Plain Old Java Object

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// we use Lombok to make the boilerplate code much better for the Java Users. this reduces the creation of
// getters, setters and constructors.

// Lombok achieves this by generating this code automatically during compilation based on annonations
// when you run your class, the generated methods are available to use.

@Document(collection = "jounraldb")
@Data
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
