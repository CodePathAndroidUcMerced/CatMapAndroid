package com.example.androidcodepath_catmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import fragments.Course;
import fragments.Home;
import fragments.Map;
import fragments.Profile;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:{
                ParseUser.logOut();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_course:
                        Toast.makeText(MainActivity.this, "Course!", Toast.LENGTH_SHORT).show();
                        fragment = new Course();
                        break;
                    case R.id.action_home:
                        Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        fragment = new Home();
                        break;
                    case R.id.action_map:
                        Toast.makeText(MainActivity.this, "Map!", Toast.LENGTH_SHORT).show();
                        fragment = new Map();
                        break;
                    case R.id.action_profile:
                    default:
                        //To do Update fragment
                        Toast.makeText(MainActivity.this, "profile!", Toast.LENGTH_SHORT).show();
                        fragment = new Profile(getApplicationContext());
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);

    }


}