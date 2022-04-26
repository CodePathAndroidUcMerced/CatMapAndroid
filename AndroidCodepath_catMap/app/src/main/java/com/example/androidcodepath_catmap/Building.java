package com.example.androidcodepath_catmap;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

@ParseClassName("Building")

public class Building extends ParseObject {

    public static String KEY_NAME = "name";
    public static String KEY_DESCRIPTION ="description";
    public static String KEY_B_BUILDING  = "b_building";
    public static String KEY_FLOOR1 = "floor1";
    public static String KEY_FLOOR2 = "floor2";
    public static String KEY_FLOOR3 = "floor3";
    public static String  KEY_LOCATION = "location";



    public String getName(){return getString(KEY_NAME);}
    public  void setName(String name) {  put (KEY_NAME , name); }

    public String getDescription(){return getString(KEY_DESCRIPTION);}
    public  void setDescription(String description) { put (KEY_DESCRIPTION , description); }

    public String getB_building() {return getString(KEY_B_BUILDING );}
    public void setB_building(String b_building){ put (KEY_B_BUILDING, b_building);}

    public String getFloor1() {return getString(KEY_FLOOR1);}
    public void setFloor1 ( String floor1) {put (KEY_FLOOR1, floor1);}

    public String getFloor2() {return getString(KEY_FLOOR2);}
    public void setFloor2 ( String floor2) {put (KEY_FLOOR2, floor2);}

    public String getFloor3() {return getString(KEY_FLOOR3);}
    public void setFloor3 ( String floor3) {put (KEY_FLOOR3, floor3);}

    public ParseGeoPoint getLocation() {return getParseGeoPoint(KEY_LOCATION);}
    public void setLocation ( ParseGeoPoint location) {put ( KEY_LOCATION,location);}









}
