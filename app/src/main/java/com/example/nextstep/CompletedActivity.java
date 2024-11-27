package com.example.nextstep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CompletedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);

        // Set Status Bar Color
        getWindow().setStatusBarColor(getResources().getColor(R.color.app_bar_color, this.getTheme()));

        // Back Button Listener
        ImageView backButton = findViewById(R.id.backbutton1);
        backButton.setOnClickListener(v -> {
            // Navigate to the Home Activity
            Intent intent = new Intent(CompletedActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Optional: Close this activity
        });
    }
}
