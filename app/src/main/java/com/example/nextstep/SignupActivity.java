package com.example.nextstep;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

  //declare all needed variavbles so errors don't appear later w tkon bayna fl class kolo
    private EditText usernamefield, emailfield, passwordfield, phonenumberfield;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        // fields without their type so set error can work
        usernamefield = findViewById(R.id.username);
        emailfield = findViewById(R.id.email);
        passwordfield = findViewById(R.id.password);
        phonenumberfield = findViewById(R.id.phonenumber);
        Button signupButton = findViewById(R.id.signup_button);

       //el hyegy mn el fields el fl design htb2a stored fl variables el t7t
        signupButton.setOnClickListener(v -> {
            String username = usernamefield.getText().toString().trim();
            String email = emailfield.getText().toString().trim();
            String password = passwordfield.getText().toString().trim();
            String phonenumber = phonenumberfield.getText().toString().trim();
            // switch screen if all validations passed
            if (checkUsername(username) && checkEmail(email) && checkPassword(password) && checkPhoneNumber(phonenumber)) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close SignupActivity
            }
        });
    }

    private boolean checkUsername(String username) {
        //is empty checks if nothing is written inside the field
        //textutils checks the string
        if (TextUtils.isEmpty(username)) {
            //set error tzhr red warning circle fl field
            usernamefield.setError("Username cannot be empty");
            return false;
        } else if (username.length() < 3 || username.length() > 8) {
            usernamefield.setError("Username must be between 3 and 8 characters");
            return false;
        }
        return true;
    }

    private boolean checkEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            emailfield.setError("Email cannot be empty");
            return false;
            //patterns.EMAIL_ADDRESS checks if the email is valid w di built in function w matcher (applies the pattern to the string email) matches teh paramter to the pattern
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailfield.setError("Invalid email format");
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password) {
        String passPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$"; // password should be 6 characters and follow a standard
        Pattern pattern = Pattern.compile(passPattern); // will read the regular expression pattern and compile it
        Matcher matcher = pattern.matcher(password);// matcher applies the pattern to the string password

        if (TextUtils.isEmpty(password)) {
            passwordfield.setError("Password cannot be empty");
            return false;
        } else if (!matcher.matches()) {
            passwordfield.setError("Password must be at least 6 characters, include an uppercase letter, a lowercase letter, a number, and a special character");
            return false;
        }
        return true;
    }

    private boolean checkPhoneNumber(String phoneNumber) {
        String phonePattern = "^\\d{11}$"; // Ensure the phone number is 11 digits
        Pattern pattern = Pattern.compile(phonePattern);// will read the regular expression pattern and compile it
        Matcher matcher = pattern.matcher(phoneNumber);

        if (TextUtils.isEmpty(phoneNumber)) {
            phonenumberfield.setError("Phone number cannot be empty");
            return false;
        } else if (!matcher.matches()) {
            phonenumberfield.setError("Phone number must be 11 digits");
            return false;
        }
        return true;
    }
}
