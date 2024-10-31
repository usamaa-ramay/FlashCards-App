package com.example.flashcardsapp.ui.addFlashcards;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.flashcardsapp.R;
import com.example.flashcardsapp.data.model.FlashCardsData;
import com.example.flashcardsapp.databinding.ActivityAddFlashcardsBinding;
import com.example.flashcardsapp.utils.FlashcardViewModel;

public class AddFlashcardsActivity extends AppCompatActivity {
    private ActivityAddFlashcardsBinding binding;
    private FlashcardViewModel flashcardViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddFlashcardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        flashcardViewModel = new ViewModelProvider(this).get(FlashcardViewModel.class);

        binding.saveFlashcardBtn.setOnClickListener(v ->
                saveFlashcard());
    }

    private void saveFlashcard() {
        String question = binding.questionInputET.getText().toString().trim();
        String answer = binding.answerInputET.getText().toString().trim();

        if (!question.isEmpty() && !answer.isEmpty()) {
            FlashCardsData flashcard = new FlashCardsData(question, answer);
            flashcard.setQuestion(question);
            flashcard.setAnswer(answer);
            flashcardViewModel.insert(flashcard);
            Toast.makeText(this, "Flashcard saved!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Please enter both question and answer", Toast.LENGTH_SHORT).show();
        }
    }
}