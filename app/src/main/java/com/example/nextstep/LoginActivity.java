package com.example.nextstep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView signupLink = findViewById(R.id.signuplink);
        Button loginButton = findViewById(R.id.loginButton);
        EditText userfield=findViewById(R.id.usernamebar);
        EditText passfield=findViewById(R.id.passwordbar);

        // go to signup screen when link is clicked
        //onclick listener waits for a specific event (zy el kan fi javascript mstani action)
        signupLink.setOnClickListener(v -> {
            // intent navigates from screen to screen constructor takes your current screen then the destination you want to switch too
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);// di btwadek lel screen el destination
        });

        // Validate login on button click
        //store userfield text and passwordfield text in variables username&password
        loginButton.setOnClickListener(v -> {
            //getText() returns the text in fiels w tostring() converts it to a tring w trim removes any space
            String username = userfield.getText().toString().trim();
            String password = passfield.getText().toString().trim();

            // Check if username and password are correct and match admin
            if (username.equals("abuzeidamar") && password.equals("1234")) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Close LoginActivity
            } else { // incase ana ktbt w7da mnhom ghlt aw w7da fadya
                if (!username.equals("admin")) {
                    //set error is the red circle that appears in the field
                    userfield.setError("Invalid username");
                }
                if (!password.equals("admin")) {
                    passfield.setError("Invalid password");
                }
            }
        });
    }
}


