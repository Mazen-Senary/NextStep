package com.example.nextstep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
     FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView signupLink = findViewById(R.id.signuplink);
        TextView forgotPassword = findViewById(R.id.forgotPassword);
        Button loginButton = findViewById(R.id.loginButton);
        EditText userfield = findViewById(R.id.usernamebar);
        EditText passfield = findViewById(R.id.passwordbar);
        mAuth = FirebaseAuth.getInstance();

        // go to signup screen when link is clicked
        signupLink.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(intent); // Navigate to SignupActivity
        });
        // go to forgot password screen when link is clicked
        forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
            startActivity(intent);
        });

        // Validate login on button click
        loginButton.setOnClickListener(v -> {
            // store userfield text and passwordfield text in variables username&password
                String username = userfield.getText().toString().trim();
                String password = passfield.getText().toString().trim();
                if (username.isEmpty()) {
                    userfield.setError("Username is required");
                    return;
                }
                if (password.isEmpty()) {
                    passfield.setError("Password is required");
                    return;
                }

                // Firebase authentication
                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(LoginActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, navigate to HomeActivity
                                Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish(); // Close LoginActivity
                            } else {
                                // If sign-in fails, display a message to the user
                                String errorMessage = task.getException() != null ? task.getException().getMessage() : "Login failed. Please check your credentials.";
                                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        });
            });


    }
}
