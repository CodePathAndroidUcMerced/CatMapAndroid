package com.example.androidcodepath_catmap;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseFile;
import com.parse.ParseUser;

import fragments.Course;
import fragments.Home;
import fragments.Map;
import fragments.Profile;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private MenuItem profileMenu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        profileMenu = menu.findItem(R.id.profileMenu);


        ParseFile image = ParseUser.getCurrentUser().getParseFile("image"); // get image
        if(image != null) {
            Glide.with(getApplicationContext()).load(image.getUrl()).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    menu.findItem(R.id.profileMenu).setIcon(resource);
                }
            }); // load image
        }
        else{
            Log.d("Keev", "this failed");
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()){
            case R.id.logout:{
                ParseUser.logOut();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                return true;
            }
            case R.id.profileMenu:{
                fragment = new Profile(getApplicationContext());
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                bottomNavigationView.setSelectedItemId(R.id.action_profile);
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.cat);
        getSupportActionBar().setLogo(R.drawable.smolcat);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_course:
                        //Toast.makeText(MainActivity.this, "Course!", Toast.LENGTH_SHORT).show();
                        fragment = new Course();
                        break;
                    case R.id.action_home:
                        //Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        fragment = new Home();
                        break;
                    case R.id.action_map:
                        //Toast.makeText(MainActivity.this, "Map!", Toast.LENGTH_SHORT).show();
                        fragment = new Map();
                        break;
                    case R.id.action_profile:
                    default:
                        //To do Update fragment
                        //Toast.makeText(MainActivity.this, "profile!", Toast.LENGTH_SHORT).show();
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

    public void setMenuPic(Drawable d){
        profileMenu.setIcon(d);
    }


}