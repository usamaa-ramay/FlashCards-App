package com.example.flashcardsapp.utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FlashcardViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public FlashcardViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FlashcardViewModel.class)) {
            return (T) new FlashcardViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
