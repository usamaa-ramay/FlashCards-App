package com.example.flashcardsapp.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "flashcards")
public class FlashCardsData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String question;
    private String answer;

    // Constructor
    public FlashCardsData(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

