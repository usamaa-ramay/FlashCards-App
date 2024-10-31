package com.example.flashcardsapp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.flashcardsapp.data.model.FlashCardsData;

import java.util.List;

@Dao
public interface FlashcardDao {

    @Insert
    void insert(FlashCardsData flashcard);

    @Query("SELECT * FROM flashcards")
    LiveData<List<FlashCardsData>> getAllFlashcards();

//    @Delete
//    void delete(FlashCardsData flashcard);
}

