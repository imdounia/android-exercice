package com.example.andoirdproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CharacterService {
    @GET("character")
    Call<List<Character>> getCharacters();
}
