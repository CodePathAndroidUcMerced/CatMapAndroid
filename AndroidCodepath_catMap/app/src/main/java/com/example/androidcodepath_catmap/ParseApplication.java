package com.example.androidcodepath_catmap;


import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(classes.class);
        ParseObject.registerSubclass(Building.class);
        ParseObject.registerSubclass(Room.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("eQpAhZ4ZXtGuo1swB7U6h665c6U0iIYAswvx1dP7")
                .clientKey("zkN4VXFQ1cj0IkiNjSwiwOWwgsdryN1mdHejKTTP")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
