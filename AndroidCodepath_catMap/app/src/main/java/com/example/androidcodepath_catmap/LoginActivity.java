package com.example.androidcodepath_catmap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {


    public static  final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;
    private TextView tvError;

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
        tvError = findViewById(R.id.tvError);
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
                    tvError.setText(getString(R.string.error));

                    return;
                }
                // navigate to the manin activity if the user has signe in
                //pop();
                goMainActivity();
                Toast.makeText(com.example.androidcodepath_catmap.LoginActivity.this, "success!", Toast.LENGTH_SHORT);
            }
        });
    }
    private void pop() {



        // json object
        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAssets());
            JSONArray jsonArray = jsonObject.getJSONArray("fall2020");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userData = jsonArray.getJSONObject(i);
                Log.i(TAG, "Atttempting to save data user" + jsonArray.length()+  userData.has("lecture_crn")  );




                classes course = new classes();

                if (userData.has("crn")){course.setCrn(userData.getString("crn"));}
                if (userData.has("course_id")){course.setCourse_id(userData.getString("course_id"));}
                if (userData.has("course_name")){course.setCourse_name(userData.getString("course_name"));}
                if (userData.has("subject")){course.setSubject(userData.getString("subject"));}
                if (userData.has("units")){course.setUnits(userData.getString("units"));}
                if (userData.has("type")){course.setType(userData.getString("type"));}
                if (userData.has("days")){course.setDays(userData.getString("days"));}
                if (userData.has("hours")){course.setHours(userData.getString("hours"));}
                if (userData.has("room")){course.setRoom(userData.getString("room"));}
                if (userData.has("dates")){course.setDates(userData.getString("dates"));}
                if (userData.has("instructor")){course.setInstructor(userData.getString("instructor"));}
                if (userData.has("capacity")){course.setCapacity(userData.getString("capacity"));}
                if (userData.has("enrolled")){course.setEnrolled(userData.getString("enrolled"));}
                if (userData.has("available")){course.setAvailable(userData.getString("available"));}
                if (userData.has("final_type")){course.setFinal_type(userData.getString("final_type"));}
                if (userData.has("final_days")){course.setFinal_day(userData.getString("final_days"));}
                if (userData.has("final_hours")){course.setFinal_hours(userData.getString("final_hours"));}
                if (userData.has("final_room")){course.setFinal_room(userData.getString("final_room"));}
                if (userData.has("final_dates")){course.setFinal_dates(userData.getString("final_dates"));}
                if (userData.has("term")){course.setTerm(userData.getString("term"));}
                if (userData.has("lecture_crn")){course.setLecture_crn(userData.getString("lecture_crn"));
                   Log.i(TAG,"lecture crn"+ userData.getString("lecture_crn") );}



                course.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null ) {
                            Log.i(TAG, "Atttempting to save data user");
                            Log.e(TAG, "Error while saving", e);

                        }
                        Log.i(TAG, "Post save was successful!!");

                    }
                });

           }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


    private String JsonDataFromAssets() {
        String json = null;

        try {
            InputStream inputStream = getAssets().open("user2.json");

            int sizeofFile = inputStream.available();
            byte[] bufferData = new byte[sizeofFile];

            inputStream.read(bufferData);
            inputStream.close();

            json = new String(bufferData, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }


    private void goMainActivity(){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}