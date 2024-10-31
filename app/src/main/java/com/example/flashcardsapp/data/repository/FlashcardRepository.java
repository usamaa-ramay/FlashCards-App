package com.example.flashcardsapp.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.flashcardsapp.data.database.FlashcardDao;
import com.example.flashcardsapp.data.database.FlashcardDatabase;
import com.example.flashcardsapp.data.model.FlashCardsData;

import java.util.List;

public class FlashcardRepository {
    private FlashcardDao flashcardDao;
    private LiveData<List<FlashCardsData>> allFlashcards;

    public FlashcardRepository(Application application) {
        FlashcardDatabase db = FlashcardDatabase.getDatabase(application);
        flashcardDao = db.flashcardDao();
        allFlashcards = flashcardDao.getAllFlashcards();
    }

    public LiveData<List<FlashCardsData>> getAllFlashcards() {
        return allFlashcards;
    }

    public void insert(final FlashCardsData flashcard) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                flashcardDao.insert(flashcard);
            }
        }).start();
    }

//    public void delete(final FlashCardsData flashcard) {
//        FlashcardDatabase.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                flashcardDao.delete(flashcard);
//            }
//        });
//    }
}
