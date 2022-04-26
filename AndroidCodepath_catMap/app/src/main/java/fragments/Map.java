package fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.androidcodepath_catmap.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Map extends Fragment {

    public static  final String TAG = "Map";

    private GoogleMap mMap;

    // creating a variable
    // for search view.
    SearchView searchView;

    // below are the latitude and longitude
    // of 4 different locations.
    LatLng KL = new LatLng(37.36618895996571, -120.42428965977152);
    LatLng COB2 = new LatLng(37.36739786659612, -120.42463031920892);
    LatLng COB1 = new LatLng(37.367146012654416, -120.42319638064686);
    LatLng SSM = new LatLng(37.367473422613784, -120.42175451977229);

    // creating array list for adding all our locations.
    private ArrayList<LatLng> locationArrayList;
    public Marker[] allMarker;






    String [] location_name = {"KL", "COB2", "COB1", "SSM"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Initialize view
        View view=inflater.inflate(R.layout.fragment_map, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);


        // in below line we are initializing our array list.
        locationArrayList = new ArrayList<>();

        // on below line we are adding our
        // locations in our array list.
        locationArrayList.add(KL);
        locationArrayList.add(COB2);
        locationArrayList.add(COB1);
        locationArrayList.add(SSM);

        allMarker = new Marker[locationArrayList.size()];
        // adding on query listener for our search view.







        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;


                // inside on map ready method
                // we will be displaying all our markers.
                // for adding markers we are running for loop and
                // inside that we are drawing marker on our map.

                for (int i = 0; i < locationArrayList.size(); i++) {

                    // below line is use to add marker to each location of our array list.

                    allMarker[i]= mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title(location_name[i]));

                    // below lin is use to zoom our camera on map.
                    //hello

                    // below line is use to move our camera to the specific location.
                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
                    //mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                    Log.i(TAG,"View location name" + location_name[i]);
                }

                //set start location and zooom
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(KL, 15.0f));
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
                String location = searchView.getQuery().toString();
                Log.i(TAG,"View location" + location);
                Log.i(TAG,"View  1 " + location_name.length);


                for (int i = 0; i < location_name.length; i++) {
                    Log.i(TAG,"View  1 " + location + " " + location_name[i]);

                    if (location.equals(location_name[i])){
                        allMarker[i].showInfoWindow();

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

    }