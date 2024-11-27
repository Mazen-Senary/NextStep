package com.example.nextstep;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // Set the status bar color to transparent so it doesnt show purple part
        //check version of android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_home);
        // intialize the bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Default fragment is home

        //checks if this is the first time the activity is created and if it is true will replace  the current fragment with  home fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

        // checks if items are pressed or clicked or used in the navigation bar
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // to hide and display elements of home page (welocme text & search bar) they should only appear in the home page
            View welcomeText = findViewById(R.id.welcomeText);
            View searchBar = findViewById(R.id.search);

             //if condition to switch fragments based on what item is clicked in the navigation bar
            if (item.getItemId() == R.id.notification) {
                selectedFragment = new NotificationFragment();
                // so homepage elements like welcome text and search bar dont show
                welcomeText.setVisibility(View.GONE);
                searchBar.setVisibility(View.GONE);
            } else if (item.getItemId() == R.id.home) {
                selectedFragment = new HomeFragment();
                // hompage elements only show up in home
                welcomeText.setVisibility(View.VISIBLE);
                searchBar.setVisibility(View.VISIBLE);
            } else if (item.getItemId() == R.id.profile) {
                selectedFragment = new ProfileFragment();
               // so homepage elements like welcome text and search bar dont show
                welcomeText.setVisibility(View.GONE);
                searchBar.setVisibility(View.GONE);
            }

            //if the fragment is valid replace the fragment container with selected fragment from the navigation bar
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });
        // intialize the image button to go to volunteering, training, upcoming and completed activities
        ImageButton imageButton1=findViewById(R.id.homebutton1);
        ImageButton imageButton2=findViewById(R.id.homebutton2);
        ImageButton imageButton3=findViewById(R.id.homebutton3);
        ImageButton imageButton4=findViewById(R.id.homebutton4);

        imageButton1.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, VolunteerActivity.class);
            startActivity(intent);
        });
        imageButton2.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TrainingActivity.class);
            startActivity(intent);
        });
        imageButton3.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this,UpcomingActivity.class);
            startActivity(intent);
        });
        imageButton4.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this,CompletedActivity.class);
            startActivity(intent);
        });

    }
}

