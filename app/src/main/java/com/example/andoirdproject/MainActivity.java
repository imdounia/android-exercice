package com.example.andoirdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private ImageView googleImageView;
    private ImageView facebookImageView;
    private ImageView appleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleImageView = findViewById(R.id.googleImageView);
        facebookImageView = findViewById(R.id.facebookImageView);
        appleImageView = findViewById(R.id.appleImageView);
        loginButton = findViewById(R.id.loginButton);

        googleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Connected with Google");
            }
        });

        facebookImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Connected with Facebook");
            }
        });

        appleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Connected with Apple");
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CharacterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}