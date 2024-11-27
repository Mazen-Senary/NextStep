package com.example.nextstep;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        // sets a delay at the beginning then moves next screen
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               // intent will navigate between the screens w paramters are (current screen, destination)
               Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
               startActivity(intent);
               finish();
           }
       }, 5000);
    }
}