package com.example.andoirdproject;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<Character> characterList;

    public CharacterAdapter() {
        characterList = new ArrayList<>();
    }

    public void setCharacterList(List<Character> characters) {
        characterList = characters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);

        holder.nameTextView.setText(character.getName());
        Glide.with(holder.itemView.getContext())
                .load(character.getImageUrl())
                .into(holder.characterImageView);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {

        ImageView characterImageView;
        TextView nameTextView;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);

            characterImageView = itemView.findViewById(R.id.characterImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }
}