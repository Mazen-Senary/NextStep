package com.example.nextstep;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PortfolioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        // intialize back arrow 3lshan arg3 profile fragment
        ImageView backArrow = findViewById(R.id.back_icon_p);

        backArrow.setOnClickListener(v -> {
            // Replace the current fragment with the profile fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            transaction.replace(R.id.main, new ProfileFragment()); // Replace with actual ProfileFragment
            transaction.addToBackStack(null); // Add this transaction to the back stack
            transaction.commit();
        });
    }
}
