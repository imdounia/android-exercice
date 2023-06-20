package com.example.andoirdproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterActivity extends AppCompatActivity {
    private ProgressBar progressBar;


    private Retrofit retrofit;
    private CharacterService characterService;
    private Call<CharacterRoot> tvShowRootCall;
    private static final String BASE_URL = "https://api.disneyapi.dev/";
    private Thread thread;
    private RecyclerView tvShowRecycler;
    private CharacterAdapter characterAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);


        tvShowRecycler = findViewById(R.id.charactersRecycler);

        layoutManager = new GridLayoutManager(getApplicationContext(), 2);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        characterService = retrofit.create(CharacterService.class);

        tvShowRootCall = characterService.getCharacterList(1);
        new Thread(() -> {
            try {
                Response<CharacterRoot> response = tvShowRootCall.execute();
                if (response.isSuccessful()) {
                    List<Character> characterList = response.body().getCharacterList();
                    runOnUiThread(() -> {
                        characterAdapter = new CharacterAdapter(
                                getApplicationContext(),
                                characterList
                        );

                        tvShowRecycler.setAdapter(characterAdapter);
                        tvShowRecycler.setLayoutManager(layoutManager);
                        tvShowRecycler.setHasFixedSize(true);
                        progressBar.setVisibility(View.INVISIBLE);
                    });
                }
            } catch (IOException e) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(CharacterActivity.this, "Error loading data", Toast.LENGTH_LONG).show();
                });
                e.printStackTrace();
            }
        }).start();

    }
}