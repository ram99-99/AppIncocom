package com.example.fininfocom;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;


public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input values
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (isValidCredentials(username, password)) {
                    Intent recyclerViewIntent = new Intent(LoginActivity.this, RecyclerViewActivity.class);
                    startActivity(recyclerViewIntent);
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    // insertData();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private boolean isValidCredentials(String username, String password) {
        if (username.length() < 10) {
            return false;
        }
        if (password.length() < 7) {
            return false;
        }
        // Check if the password contains at least one uppercase letter, one special character, and one numeric character
        boolean hasUppercase = false;
        boolean hasSpecialChar = false;
        boolean hasNumeric = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (isSpecialCharacter(c)) {
                hasSpecialChar = true;
            } else if (Character.isDigit(c)) {
                hasNumeric = true;
            }
        }
        return hasUppercase && hasSpecialChar && hasNumeric;
    }

    private boolean isSpecialCharacter(char c) {
        // Define a list of special characters that you consider valid
        String specialCharacters = "!@#$%^&*()_-+=<>?";

        return specialCharacters.contains(String.valueOf(c));
    }

}
