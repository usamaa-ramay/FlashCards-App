package com.example.flashcardsapp.ui.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.flashcardsapp.R;
import com.example.flashcardsapp.data.model.FlashCardsData;
import com.example.flashcardsapp.databinding.ActivityQuizBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private ActivityQuizBinding binding;
    private List<FlashCardsData> flashcards;
    private int currentCardIndex = 0;
    private int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

// Flashcards ko fetch karein (is example mein yeh intent se pass kiya gaya hai)
        flashcards = (List<FlashCardsData>) getIntent().getSerializableExtra("flashcards");
        if (flashcards == null || flashcards.isEmpty()) {
            Toast.makeText(this, "No flashcards available for the quiz.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        binding.answerbtn1.setOnClickListener(v -> onAnswerSelected(binding.answerbtn1.getText().toString().equals(getCurrentAnswer())));
        binding.answerBtn2.setOnClickListener(v -> onAnswerSelected(binding.answerBtn2.getText().toString().equals(getCurrentAnswer())));
        binding.answerBtn3.setOnClickListener(v -> onAnswerSelected(binding.answerBtn3.getText().toString().equals(getCurrentAnswer())));

        showNextFlashcard();

    }

    private void showNextFlashcard() {
        if (currentCardIndex < flashcards.size()) {
            FlashCardsData currentCard = flashcards.get(currentCardIndex);
            binding.questionTV.setText(currentCard.getQuestion());

            List<String> answers = new ArrayList<>();
            answers.add(currentCard.getAnswer()); // Add the correct answer

            final List<String> incorrectAnswersList = List.of(
                    "Open Object Programming",
                    "Overloaded Operations Programming",
                    "inherit",
                    "O(n)",
                    "implements", // Additional incorrect answers
                    "O(n^2)",
                    "Simple Query Language",
                    "It refers to the parent class",
                    "It is a placeholder for any object",
                    "Queue",
                    "Array",
                    "To create loops in code",
                    "var",
                    "To define variables",
                    "A method of looping through arrays"
            );
            List<String> availableIncorrectAnswers = new ArrayList<>(incorrectAnswersList);
            Collections.shuffle(availableIncorrectAnswers);

            int noOfIncorrectAnswers = 2;
            for (int i = 0; i < noOfIncorrectAnswers; i++) { // Change 2 to however many incorrect answers you want
                answers.add(availableIncorrectAnswers.get(i));
            }

            Collections.shuffle(answers);

            // Set the shuffled answers to the buttons
            binding.answerbtn1.setText(answers.get(0));
            binding.answerBtn2.setText(answers.get(1));
            binding.answerBtn3.setText(answers.get(2));
        } else {
            showScore();
        }
    }

    public void onAnswerSelected(boolean isCorrect) {
        if (isCorrect) {
            score++;
        }
        currentCardIndex++;
        showNextFlashcard();
    }
    private String getCurrentAnswer() {
        return flashcards.get(currentCardIndex).getAnswer();
    }
    private void showScore() {
        binding.scoreTV.setText("Your Score "+score + "/" + flashcards.size());
    }

}