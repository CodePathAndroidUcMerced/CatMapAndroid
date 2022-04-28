package fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.androidcodepath_catmap.Building;
import com.example.androidcodepath_catmap.R;
import com.example.androidcodepath_catmap.Room;
import com.example.androidcodepath_catmap.classes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Map extends Fragment {

    public List<Building> buildings;
    public List<Building> build;

    public static  final String TAG = "Map";

    private GoogleMap mMap;

    // creating a variable
    // for search view.
    SearchView searchView;
    public String roomName = new String();



    // below are the latitude and longitude
    // of 4 different locations.
//    LatLng KL = new LatLng(37.36618895996571, -120.42428965977152);
//    LatLng COB2 = new LatLng(37.36739786659612, -120.42463031920892);
//    LatLng COB1 = new LatLng(37.367146012654416, -120.42319638064686);
//    LatLng SSM = new LatLng(37.367473422613784, -120.42175451977229);

    // creating array list for adding all our locations.
    ArrayList<LatLng> locationArrayList = new ArrayList<>();
    //public Marker[] allMarker;
    ArrayList<Marker> allMarker = new ArrayList<>();
    //public  ArrayList <String> name;
    ArrayList <String>  location_name = new ArrayList<>();
    ArrayList <ParseFile> building_image = new ArrayList<>();
    ArrayList <String> description = new ArrayList<>();
    ArrayList <String> aux = new ArrayList<>();
    ArrayList<String> aux1 = new ArrayList<>();








    //String [] location_name = {"KL", "COB2", "COB1", "SSM"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Initialize view
        View view=inflater.inflate(R.layout.fragment_map, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);



        //querryBuildingName(buildingId);


        // in below line we are initializing our array list.
        //queryBuilding();





//        locationArrayList = new ArrayList<>();
//
//
//        // on below line we are adding our
//        // locations in our array list.
//        locationArrayList.add(KL);
//        locationArrayList.add(COB2);
//        locationArrayList.add(COB1);
//        locationArrayList.add(SSM);
//
       //allMarker = new Marker[locationArrayList.size()];
        // adding on query listener for our search view.







        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
//                aux.clear();
//                aux1.clear();
//                Querryname("15082");
//
//                runQuery();

                mMap = googleMap;
                queryBuilding();

                //Log.i(TAG, "classes size  " + aux.get(0) );

                //Querryname("10621");

                //buildingId = queryBuildingId(roomName);

                // inside on map ready method
                // we will be displaying all our markers.
                // for adding markers we are running for loop and
                // inside that we are drawing marker on our map.

                for (int i = 0; i < locationArrayList.size(); i++) {

                    // below line is use to add marker to each location of our array list.

                    allMarker.set(i, mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title(location_name.get(i))));

                    // below lin is use to zoom our camera on map.
                    //hello

                    // below line is use to move our camera to the specific location.
                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
                    //mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                    Log.i(TAG,"View location name" + location_name.get(i));
                }

                //set start location and zooom
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.36618895996571, -120.42428965977152), 15.0f));
            }



        });
        // Return view
        //supportMapFragment.getMapAsync((OnMapReadyCallback) this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initializing our search view.
        searchView = view.findViewById(R.id.idSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // on below line we are getting the
                // location name from search view.
                String text = searchView.getQuery().toString();
                //queryBuildingLocation(text);
                aux.clear();
                aux1.clear();
                Querryname(text);
                runQuery();

               String location = aux1.get(0);
//                Log.i(TAG,"View index" + aux.get(0) + aux1.get(0));
//                Log.i(TAG,"View location" + location);
//                Log.i(TAG,"View  1 " + location_name.size());

//                Log.i(TAG, "room name check  0 " + aux.get(0));
//                for (int i = 0; i < aux.size();i++) {
//                    Log.i(TAG, "room name check  " + aux.get(i));
//                }


                for (int i = 0; i < location_name.size(); i++) {
                    Log.i(TAG,"View  1 " + location + " " + location_name.get(i));

                    if (location.equals(location_name.get(i))){
                        allMarker.get(i).showInfoWindow();

                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locationArrayList.get(i), 15));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));


                        Log.i(TAG,"View  2"+ location);
                    }

                }


                // }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });






      }



    private void queryBuilding(){
        ParseQuery<Building> query = ParseQuery.getQuery(Building.class);

        query.findInBackground(new FindCallback<Building>() {
            @Override
            public void done(List<Building> buildings, ParseException e) {


                if (e != null){
                    Log.e(TAG, "  issue with gettimg Posts",e);

                }
                //Log.i(TAG,"buildings size  " + buildings.size());
                //buildings.addAll(build);




                for (int i = 0; i < buildings.size(); i++){
                    location_name.add(buildings.get(i).getName());
                    ParseGeoPoint geop = buildings.get(i).getParseGeoPoint("location");
                    double lat = geop.getLatitude();
                    double lon = geop.getLongitude();
                    locationArrayList.add(new LatLng(lat,lon));
                    description.add(buildings.get(i).getDescription());
                    //Log.i(TAG,"name  " + location_name.get(i));
                    //Log.i(TAG,"description  " + description.get(i));
                    //Log.i(TAG,"geolocation  " + locationArrayList.get(i));

                    allMarker.add(mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title(location_name.get(i))));
                    //Log.i(TAG,"Marker added  " + location_name.get(i));

                    // below lin is use to zoom our camera on map.
                    //hello

                    // below line is use to move our camera to the specific location.
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                    //Log.i(TAG,"View location name" + location_name.get(i));


                }

            }

        });
    }

    public void Querryname( String crn) {

        ParseQuery<classes> query = ParseQuery.getQuery(classes.class);
        //query.include(Course.KEY_USER);
        query.whereEqualTo("crn", crn);


        query.setLimit(1);
        try {
            List<classes> result = query.find();

            aux.add(result.get(0).getRoom());
            Log.i(TAG, "room name  " + aux.get(0));


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void runQuery() {
        ParseQuery<Room> innerQuery = ParseQuery.getQuery(Room.class);
        innerQuery.include("b_id");
        Log.i(TAG, "name of the room  " + aux.get(0));
        innerQuery.whereEqualTo("name", aux.get(0));

        try {
            List<Room> room =  innerQuery.find();
            //point = room.get(0).getParseObject("b_id");
            for(Room rooms : room) {

                aux1.add((String) rooms.getBid().get("name"));
                Log.i(TAG, "room 12 name  " + rooms.getBid().get("name")+ aux1.get(0));

            }
            room.clear();


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Building");
//        query.whereEqualTo("objectId", ParseQuery.getQuery(Building.class));
//
//        query.findInBackground((commentList, e) -> {
//            if(e == null){
//                for (ParseObject comment : commentList) {
//                    ParseObject post = comment.getParseObject("objectId");
//                    Log.i("Object found ",post.getObjectId());
//                }
//            }else{
//                Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });


    public void querryB() {
//        ParseQuery <Room> query = ParseQuery.getQuery(Room.class);
//        query.whereEqualTo("name",aux.get(0));
//        query.setLimit(1);
//
//        ParseQuery<Building> query4 = ParseQuery.getQuery(Building.class);
//        query4.whereMatchesQuery("Room",query);
//        //query.include("b_id.Building");
//
//
//        query4.findInBackground()







    }

//    public void querryC (){
//
//        ParseQuery<Building> query2 = ParseQuery.getQuery(Building.class);
//            query2.whereEqualTo("objectId", aux.get(1));
//            query2.setLimit(1);
//        try {
//            List<Building> result = query2.find();
//                aux.add(result.get(0).getName());
//                Log.i(TAG, "building name  " + aux.get(2) );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }

    }
