package fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidcodepath_catmap.CourseAdapter;
import com.example.androidcodepath_catmap.R;
import com.example.androidcodepath_catmap.classes;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    ArrayList<String> crn = new ArrayList<>();
    ArrayList<String> courseId = new ArrayList<>();
    ArrayList<String> courseName = new ArrayList<>();

    private RecyclerView rvcourse;
    private TextView tvClassNum;
    protected CourseAdapter adapter;
    protected List<classes> userCourses;
    private ParseUser user = ParseUser.getCurrentUser();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvcourse = view.findViewById(R.id.rvcourse);
        tvClassNum = view.findViewById(R.id.tvClassNum);
        userCourses = new ArrayList<>();
        adapter = new CourseAdapter(getContext(),userCourses);
        ArrayList<String> classList;

        rvcourse.setAdapter(adapter);
        rvcourse.setLayoutManager(new LinearLayoutManager(getContext()));


        classList = (ArrayList) user.get("classList");

        queryClasses(ParseUser.getCurrentUser(), classList);
        refreshTotalClasses(classList);
    }

    public void refreshTotalClasses(ArrayList<String> classList){
        if(user.get("classList") == null){
            Log.d("Keev", "BOOOOOO");
            return;
        }
        else{
            for (String s : classList){
                Log.d("Keev", "inside " + s);
            }
            tvClassNum.setText("Total Classes: " + classList.size());
        }
    }

    private void queryClasses(ParseUser user, ArrayList<String> classList){
        ParseQuery<classes> query = ParseQuery.getQuery(classes.class);
        query.whereContainedIn("objectId", classList);
        query.orderByAscending("course_id");

        query.findInBackground(new FindCallback<classes>() {
            @Override
            public void done(List<classes> courses, ParseException e) {
                if (e != null){
                    Log.e("Keev", "issue with gettimg Posts",e);
                    return;
                }
                if(courses.isEmpty()){
                    Log.d("Keev", "wtfff");
                    return;
                }
                userCourses.addAll(courses);
                adapter.notifyDataSetChanged();
                Log.d("Keev", "finished all");
            }
        });
    }
}
