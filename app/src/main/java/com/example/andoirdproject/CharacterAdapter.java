package com.example.andoirdproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterHolder> {

    private final Context context;
    private final List<Character> characterList;

    public CharacterAdapter(Context context, List<Character> characterList) {
        this.context = context;
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public CharacterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_character,
                parent,
                false
        );
        return new CharacterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterHolder holder, int position) {

        final Character character = characterList.get(position);
        holder.nameText.setText(character.getName());

        Glide.with(context)
                .load(character.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public static class CharacterHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView nameText;

        public CharacterHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_thumbnail_path);
            nameText = itemView.findViewById(R.id.name);
        }
    }
}