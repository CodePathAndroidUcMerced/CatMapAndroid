package com.example.androidcodepath_catmap;


import com.parse.ParseClassName;
import com.parse.ParseObject;
@ParseClassName("Room")

public class Room extends ParseObject {

    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION ="description";
    public static final String KEY_BID ="b_id";

    public String getName (){return getString(KEY_NAME); }
    public String getDescription (){ return getString(KEY_DESCRIPTION);}
    public String getBid (){return getParseObject(KEY_BID);}




}
