package fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidcodepath_catmap.CourseAdapter;
import com.example.androidcodepath_catmap.R;
import com.example.androidcodepath_catmap.classes;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    private Context context; //used for image functionality

    TextView username;
    ImageView profileImg;
    private RecyclerView rvcourse;
    protected CourseAdapter adapter;
    protected List<classes> userCourses;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile(Context context) {
        // Required empty public constructor
        this.context = context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile(getContext());
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ParseUser User = ParseUser.getCurrentUser(); // current user
        username = view.findViewById(R.id.username); // get username from xml
        profileImg = view.findViewById(R.id.profileImg); // get username from xml
        userCourses = new ArrayList<>();
        adapter = new CourseAdapter(getContext(), userCourses);

        username.setText(User.getUsername()); // set username text

        ParseFile image = User.getParseFile("image"); // get image
        if(image != null) {
            Glide.with(context).load(image.getUrl()).into(profileImg); // load image
        }
        else{
            Log.d("Keev", "this shit failed");
        }

        // ADD ARRAY TO DATABASE
        //User.addAllUnique("classList", Arrays.asList("10124", "15159"));
        //User.saveInBackground();


    }
}