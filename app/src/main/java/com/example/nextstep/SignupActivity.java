package com.example.nextstep;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    // Declare all needed variables so errors don't appear later throughout the class
    private EditText usernamefield, emailfield, passwordfield, phonenumberfield;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if the user is already logged in and redirect to HomeActivity if they are
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Close SignupActivity if the user is already logged in
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        // Initialize all the input fields
        usernamefield = findViewById(R.id.username);
        emailfield = findViewById(R.id.email);
        passwordfield = findViewById(R.id.password);
        phonenumberfield = findViewById(R.id.phonenumber);
        Button signupButton = findViewById(R.id.signup_button);
        mAuth = FirebaseAuth.getInstance();

        // Set click listener for the signup button
        signupButton.setOnClickListener(v -> {
            String username = usernamefield.getText().toString().trim();
            String email = emailfield.getText().toString().trim();
            String password = passwordfield.getText().toString().trim();
            String phonenumber = phonenumberfield.getText().toString().trim();

            // Check if all fields pass validation
            if (checkUsername(username) && checkEmail(email) && checkPassword(password) && checkPhoneNumber(phonenumber)) {
                // Create the user with email and password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Account creation was successful
                                    Toast.makeText(SignupActivity.this, "Account has been created.", Toast.LENGTH_SHORT).show();

                                    // Send the user to LoginActivity
                                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish(); // Close SignupActivity
                                } else {
                                    // If sign-up fails, display a message to the user
                                    Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                // One or more validation checks failed
                Toast.makeText(SignupActivity.this, "Please check your input fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkUsername(String username) {
        // Check if the username field is empty or invalid length
        if (TextUtils.isEmpty(username)) {
            usernamefield.setError("Username cannot be empty");
            return false;
        } else if (username.length() < 3 || username.length() > 8) {
            usernamefield.setError("Username must be between 3 and 8 characters");
            return false;
        }
        return true;
    }

    private boolean checkEmail(String email) {
        // Check if the email field is empty or invalid format
        if (TextUtils.isEmpty(email)) {
            emailfield.setError("Email cannot be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailfield.setError("Invalid email format");
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password) {
        // Regex pattern to ensure password is strong
        String passPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
        Pattern pattern = Pattern.compile(passPattern);
        Matcher matcher = pattern.matcher(password);

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
        // Regex pattern to check if phone number has exactly 11 digits
        String phonePattern = "^\\d{11}$";
        Pattern pattern = Pattern.compile(phonePattern);
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
