//package com.example.androidcodepath_catmap;
//
//import static java.security.AccessController.getContext;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.parse.ParseException;
//import com.parse.ParseFile;
//import com.parse.ParseUser;
//import com.parse.SaveCallback;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import android.content.Context;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.AccessControlContext;
//import java.util.ArrayList;
//
//public class populateParse {
//
//    public static final String TAG = "PoP";
//
//
//
//
//private void pop() {
//
//
//
//    // json object
//    try {
//        JSONObject jsonObject = new JSONObject(JsonDataFromAssets());
//        JSONArray jsonArray = jsonObject.getJSONArray("fall2020");
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject userData = jsonArray.getJSONObject(i);
//            classes course = new classes();
//            //if (userData.getString("type").equals("LECT"))
//            course.setCrn(userData.getString("crn"));
//            course.setCourse_id(userData.getString("course_id"));
//            course.setCourse_name(userData.getString("course_name"));
//            course.setSubject(userData.getString("subject"));
//            course.setUnits(userData.getString("units"));
//            course.setType(userData.getString("type"));
//            course.setDays(userData.getString("days"));
//            course.setHours(userData.getString("hours"));
//            course.setRoom(userData.getString("room"));
//            course.setDates(userData.getString("dates"));
//            course.setInstructor(userData.getString("instructor"));
//            course.setCapacity(userData.getString("capacity"));
//            course.setEnrolled(userData.getString("enrolled"));
//            course.setAvailable(userData.getString("available"));
//            course.setFinal_type(userData.getString("final_type"));
//            course.setFinal_day(userData.getString("final_days"));
//            course.setFinal_hours(userData.getString("final_hours"));
//            course.setFinal_room(userData.getString("room"));
//            course.setDates(userData.getString("dates"));
//            course.setTerm(userData.getString("term"));
//            course.setLecture_crn(userData.getString("lecture_crn"));
//            course.saveInBackground(new SaveCallback() {
//                @Override
//                public void done(ParseException e) {
//                    if (e != null ) {
//                        Log.e(TAG, "Error while saving", e);
//
//                    }
//                    Log.i(TAG, "Post save was successful!!");
//
//                }
//            });
//
//        }
//    } catch (JSONException e) {
//        e.printStackTrace();
//    }
//
//
//
//}
//
//        private String JsonDataFromAssets() {
//            String json = null;
//
//            try {
//                InputStream inputStream = getAssets().open("user.json");
//
//                int sizeofFile = inputStream.available();
//                byte[] bufferData = new byte[sizeofFile];
//
//                inputStream.read(bufferData);
//                inputStream.close();
//
//                json = new String(bufferData, "UTF-8");
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//
//            return json;
//        }
//
//
//
//
//}
