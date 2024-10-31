package com.example.flashcardsapp.ui.addFlashcards;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcardsapp.data.model.FlashCardsData;
import com.example.flashcardsapp.databinding.ItemFlashcardBinding;

import java.util.ArrayList;
import java.util.List;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder> {

    private List<FlashCardsData> flashcards = new ArrayList<>();
    @NonNull
    @Override
    public FlashcardAdapter.FlashcardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemFlashcardBinding binding = ItemFlashcardBinding.inflate(inflater, parent, false);
        return new FlashcardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FlashcardAdapter.FlashcardViewHolder holder, int position) {
        FlashCardsData flashcard = flashcards.get(position);
        holder.binding.questionTV.setText(flashcard.getQuestion());
        holder.binding.answerTV.setText(flashcard.getAnswer());
    }

    @Override
    public int getItemCount() {
        return flashcards.size();
    }

    public void setFlashcards(List<FlashCardsData> flashcards) {
        this.flashcards = flashcards;
        notifyDataSetChanged();
    }

    public class FlashcardViewHolder extends RecyclerView.ViewHolder {
        private final ItemFlashcardBinding binding;
        public FlashcardViewHolder(@NonNull ItemFlashcardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
