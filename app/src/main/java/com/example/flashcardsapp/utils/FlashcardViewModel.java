package com.example.flashcardsapp.utils;
import android.app.Application;

import androidx.annotation.NonNull;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.flashcardsapp.data.model.FlashCardsData;
import com.example.flashcardsapp.data.repository.FlashcardRepository;

import java.io.Closeable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FlashcardViewModel extends ViewModel {
    private FlashcardRepository repository;
    public LiveData<List<FlashCardsData>> allFlashcards;

    public FlashcardViewModel(Application application) {
        repository = new FlashcardRepository(application);
        allFlashcards = repository.getAllFlashcards();
    }

    public LiveData<List<FlashCardsData>> getAllFlashcards() {
        return allFlashcards;
    }

    public void insert(FlashCardsData flashcard) {

        repository.insert(flashcard);
    }

//    public void delete(FlashCardsData flashcard) {
//        repository.delete(flashcard);
//    }
//
//    public static class FlashcardViewModelFactory implements ViewModelProvider.Factory {
//        private final FlashcardRepository repository;
//
//        public FlashcardViewModelFactory(FlashcardRepository repository) {
//            this.repository = repository;
//        }
//
//        @NonNull
//        @Override
//        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//            if (modelClass.isAssignableFrom(FlashcardViewModel.class)) {
//                return (T) new FlashcardViewModel(repository);
//            }
//            throw new IllegalArgumentException("Unknown ViewModel class");
//        }
//    }
}