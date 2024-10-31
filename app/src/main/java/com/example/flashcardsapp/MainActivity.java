package com.example.flashcardsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcardsapp.data.model.FlashCardsData;
import com.example.flashcardsapp.databinding.ActivityMainBinding;
import com.example.flashcardsapp.ui.addFlashcards.FlashcardAdapter;
import com.example.flashcardsapp.ui.quiz.QuizActivity;
import com.example.flashcardsapp.utils.FlashcardViewModel;
import com.example.flashcardsapp.utils.FlashcardViewModelFactory;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FlashcardViewModel flashcardViewModel;
    private FlashcardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = binding.flashcardRV;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FlashcardAdapter();
        recyclerView.setAdapter(adapter);

        // ViewModel ka instance create
        FlashcardViewModelFactory factory = new FlashcardViewModelFactory(getApplication());
        flashcardViewModel = new ViewModelProvider(this, factory).get(FlashcardViewModel.class);

        flashcardViewModel.getAllFlashcards().observe(this, new Observer<List<FlashCardsData>>() {
            @Override
            public void onChanged(List<FlashCardsData> flashCardsData) {

                adapter.setFlashcards(flashCardsData);
            }
        });

        Button addFlashcardButton = findViewById(R.id.addFlashcardBtn);
        addFlashcardButton.setOnClickListener(v -> openAddFlashcardDialog());

        binding.nextBtn.setOnClickListener(view -> {
            flashcardViewModel.getAllFlashcards().observe(this, flashcards -> {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("flashcards", (Serializable) flashcards);
                startActivity(intent);
            });
        });
    }

    private void openAddFlashcardDialog() {
        // Create and display a dialog to add flashcards
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.activity_add_flashcards, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        EditText questionInput = dialogView.findViewById(R.id.questionInputET);
        EditText answerInput = dialogView.findViewById(R.id.answerInputET);
        Button saveButton = dialogView.findViewById(R.id.saveFlashcardBtn);

        saveButton.setOnClickListener(v -> {
            String question = questionInput.getText().toString();
            String answer = answerInput.getText().toString();
            if (!question.isEmpty() && !answer.isEmpty()) {
                FlashCardsData flashcard = new FlashCardsData(question, answer);
                flashcardViewModel.insert(flashcard);
                dialog.dismiss();
            } else {
                Toast.makeText(MainActivity.this, "Please enter both question and answer", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}
