package com.example.androidcodepath_catmap;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("classes")

public class classes extends ParseObject {

    public static String KEY_SUBJECT ="subject";
    public static String KEY_CRN = "crn";
    public static String KEY_COURSE_ID = "course_id";
    public static String KEY_COURSE_NAME = "course_name";
    public static String KEY_UNITS = "units";
    public static String KEY_TYPES = "type";
    public static String KEY_DAYS = "days";
    public static String KEY_HOURS = "hours";
    public static String KEY_ROOM = "room";
    public static String KEY_DATES = "dates";
    public static String KEY_INSTRUCTOR = "instructor";
    public static String KEY_CAPACITY = "capacity";
    public static String KEY_ENROLLED="enrolled";
    public static String KEY_AVAILABLE = "available";
    public static String KEY_FINAL_TYPES = "final_type";
    public static String KEY_FINAL_DAYS ="final_days";
    public static String KEY_FINAL_HOURS ="final_hours";
    public static String KEY_FINAL_ROOM = "final_room";
    public static String KEY_FINAL_DATES = "final_dates";
    public static String KEY_TERM = "term";
    public static String KEY_LECTURE_CRN = "lecture_crn";




   public String getSubject(){return getString(KEY_SUBJECT);}
   public void setSubject(String subject){ put (KEY_SUBJECT,subject);}
   public String getCrn(){return getString(KEY_CRN);}
   public void setCrn(String crn){ put (KEY_CRN,crn);}
   public String getCourse_id(){return getString(KEY_COURSE_ID);}
   public void setCourse_id(String course_id){ put (KEY_COURSE_ID,course_id);}
    public String getCourse_name(){return getString(KEY_COURSE_NAME);}
    public void setCourse_name(String course_name){ put (KEY_COURSE_NAME,course_name);}
    public String getUnits(){return getString(KEY_UNITS);}
    public void setUnits(String units){ put (KEY_UNITS,units);}
    public String getType(){return getString(KEY_TYPES);}
    public void setType(String type){ put (KEY_TYPES,type);}
    public String getDays(){return getString(KEY_DAYS);}
    public void setDays(String days){ put (KEY_DAYS,days);}
    public String getHours(){return getString(KEY_HOURS);}
    public void setHours(String hours){ put (KEY_HOURS,hours);}
    public String getRoom(){return getString(KEY_ROOM);}
    public void setRoom(String room){ put (KEY_ROOM,room);}
    public String getDates(){return getString(KEY_DATES);}
    public void setDates(String dates){ put (KEY_DATES,dates);}
    public String getInstructor(){return getString(KEY_INSTRUCTOR);}
    public void setInstructor(String instructor){ put (KEY_INSTRUCTOR,instructor);}
    public String getCapacity(){return getString(KEY_CAPACITY);}
    public void setCapacity(String capacity){ put (KEY_CAPACITY,capacity);}
    public String getEnrolled(){return getString(KEY_ENROLLED);}
    public void setEnrolled(String enrolled){ put (KEY_ENROLLED,enrolled);}
    public String getAvailable(){return getString(KEY_AVAILABLE);}
    public void setAvailable(String available){ put (KEY_AVAILABLE,available);}
    public String getFinal_type(){return getString(KEY_FINAL_TYPES);}
    public void setFinal_type(String final_types){ put (KEY_FINAL_TYPES,final_types);}
    public String getFinal_day(){return getString(KEY_FINAL_DAYS);}
    public void setFinal_day(String final_days){ put (KEY_FINAL_DAYS,final_days);}
    public String getFinal_hours(){return getString(KEY_FINAL_HOURS);}
    public void setFinal_hours(String final_hours){ put (KEY_FINAL_HOURS,final_hours);}
    public String getFinal_room(){return getString(KEY_FINAL_ROOM);}
    public void setFinal_room(String final_room){ put (KEY_FINAL_ROOM,final_room);}
    public String getFinal_dates(){return getString(KEY_FINAL_DATES);}
    public void setFinal_dates(String final_dates){ put (KEY_FINAL_DATES,final_dates);}
    public String getTerm(){return getString(KEY_TERM);}
    public void setTerm(String term){ put (KEY_TERM,term);}
    public String getLecture_crn(){return getString(KEY_LECTURE_CRN);}
    public void setLecture_crn(String lecture_crn){ put (KEY_LECTURE_CRN,lecture_crn);}






}
