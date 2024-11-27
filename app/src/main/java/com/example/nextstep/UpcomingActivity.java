package com.example.nextstep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class UpcomingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        //  Status Bar Color 3lshan yb2a azr2
        getWindow().setStatusBarColor(getResources().getColor(R.color.app_bar_color, this.getTheme()));


        ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(UpcomingActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
