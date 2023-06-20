package com.example.andoirdproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        characterAdapter = new CharacterAdapter();
        recyclerView.setAdapter(characterAdapter);

        fetchCharacters();
    }

    private void fetchCharacters() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.disneyapi.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CharacterService characterService = retrofit.create(CharacterService.class);

        Call<List<Character>> call = characterService.getCharacters();
        call.enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                if (response.isSuccessful()) {
                    List<Character> characters = response.body();
                    if (characters != null) {
                        characterAdapter.setCharacterList(characters);
                    } else {
                        Toast.makeText(CharacterActivity.this, "No characters found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CharacterActivity.this, "Error fetching characters", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                Toast.makeText(CharacterActivity.this, "Error fetching characters", Toast.LENGTH_SHORT).show();
            }
        });

    }

}