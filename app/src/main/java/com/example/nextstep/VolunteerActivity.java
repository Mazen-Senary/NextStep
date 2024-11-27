package com.example.nextstep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VolunteerActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            //set app bar color
            getWindow().setStatusBarColor(getResources().getColor(R.color.app_bar_color));
            setContentView(R.layout.activity_volunteer);
            ImageView imageView = findViewById(R.id.back_arrow);
            imageView.setOnClickListener(v -> {
               Intent intent = new Intent(VolunteerActivity.this, HomeActivity.class);
               startActivity(intent);
            });
            }
        }


