package com.example.flashcardsapp.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.flashcardsapp.data.model.FlashCardsData;

import java.util.concurrent.Executor;

@Database(entities = {FlashCardsData.class}, version = 1)
public abstract class FlashcardDatabase extends RoomDatabase {


    private static FlashcardDatabase INSTANCE;
    public abstract FlashcardDao flashcardDao();

    public static FlashcardDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FlashcardDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FlashcardDatabase.class, "flashcard_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

