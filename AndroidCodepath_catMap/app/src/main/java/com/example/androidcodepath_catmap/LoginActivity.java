package com.example.androidcodepath_catmap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {


    public static  final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"onClick login Button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username,password);

            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"onClick Register Button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                registerUser(username,password);

            }
        });
    }

    private void registerUser(String username, String password) {
        Log.i(TAG, "Atttempting to register user" + username);
        ParseUser user = new ParseUser();
// Set core properties
        user.setUsername(username);
        user.setPassword(password);
// Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    loginUser(username,password);

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }

    private void loginUser(String username, String password){
        Log.i(TAG, "Atttempting to login user" + username);
        // Navigate to the main activity
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with login ", e);
                    Toast.makeText(com.example.androidcodepath_catmap.LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT);

                    return;
                }
                // navigate to the manin activity if the user has signe in
                goMainActivity();
                Toast.makeText(com.example.androidcodepath_catmap.LoginActivity.this, "success!", Toast.LENGTH_SHORT);
            }
        });
    }

    private void goMainActivity(){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}