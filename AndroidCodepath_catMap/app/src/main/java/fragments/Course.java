package fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcodepath_catmap.CourseAdapter;
import com.example.androidcodepath_catmap.R;
import com.example.androidcodepath_catmap.classes;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Course#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Course extends Fragment {
    public static  final String TAG = "Course";

    ArrayList<String> crn = new ArrayList<>();
    ArrayList<String> courseId = new ArrayList<>();
    ArrayList<String> courseName = new ArrayList<>();


    private RecyclerView rvcourse;
    protected CourseAdapter adapter;
    protected List<classes> allcourses;
    private TextView tvSearchPrompt;
    Spinner spinner;
    Button btSubmit;
    String[] months = {"Anthropology","Bioengineering","Biological Sciences","Chemistry","Chinese",
            "Civil Engineering","Cognitive Science","Community Research and Service","Computer Science & Engineering","Critical Race & Ethnic Studies",
            "Economics","Education","Elect. Engr. & Comp. Sci.","Engineering","English","Environmental Engineering",
            "Environmental Systems (GR)","Environmental Systems Science","French","Geography","Global Arts Studies Program","Heritage Studies","History",
            "Interdisciplinary Humanities","Japanese","Management","Materials & BioMat Sci & Engr","Materials Science & Engr","Mathematics","Mechanical Engineering",
            "Mgmt of Innov, Sust, and Tech","Natural Sciences Education","Philosophy","Physics","Political Science","Psychology","Public Health","Quantitative & Systems Biology","Sociology","Spanish","Spark","Undergraduate Studies","Writing"};




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Course() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Course.
     */
    // TODO: Rename and change types and number of parameters
    public static Course newInstance(String param1, String param2) {
        Course fragment = new Course();
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
        return inflater.inflate(R.layout.fragment_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        rvcourse = view.findViewById(R.id.rvcourse);
        spinner = view.findViewById(R.id.spinner);
        btSubmit = view.findViewById(R.id.btSubmit);
        tvSearchPrompt = view.findViewById(R.id.tvSearchPrompt);
        populateSpinnerMonth();

        // Take the instance of Spinner and
        // apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSearchPrompt.setVisibility(View.GONE);
                String month = spinner.getSelectedItem().toString();
                String mejor = month;
                allcourses = new ArrayList<>();

                adapter = new CourseAdapter(getContext(),allcourses);

                rvcourse.setAdapter(adapter);
                rvcourse.setLayoutManager(new LinearLayoutManager(getContext()));
                queryClasses(mejor);

                //Toast.makeText(getContext(), month, Toast.LENGTH_SHORT).show();
            }
        });


        //spinner.setOnItemSelectedListener(Activi);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getId() == R.id.spinner) {
                    String selectedMonth = parent.getSelectedItem().toString();
                    //String selectedMonth = parent.getItemAtPosition(position).toString();
                    //String selectedMonth = months[position];
                    //Toast.makeText(getContext(), "Selected: " + selectedMonth, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        allcourses = new ArrayList<>();
//
//        adapter = new CourseAdapter(getContext(),allcourses);
//
//        rvcourse.setAdapter(adapter);
//        rvcourse.setLayoutManager(new LinearLayoutManager(getContext()));
//        queryClasses();
    }

        private void populateSpinnerMonth() {
            //months = new DateFormatSymbols().getMonths();
            //months = new String [5]{"mathematic","physic"};
            // Creating the ArrayAdapter instance having the month list
            ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, months);
            monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Set the ArrayAdapter on the Spinner
            spinner.setAdapter(monthAdapter);
        }
        // Create the instance of ArrayAdapter
        // having the list of courses

    private void queryClasses(String mejor){
        ParseQuery<classes> query = ParseQuery.getQuery(classes.class);
        //query.include(Course.KEY_USER);
        query.whereEqualTo("subject",mejor);
        query.orderByAscending("course_id");
        query.setLimit(100);
        try {
            int count = query.count();
            //Toast.makeText(getContext(), "Count : "+count, Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        query.findInBackground(new FindCallback<classes>() {
            @Override
            public void done(List<classes> courses, ParseException e) {
                if (e != null){
                    Log.e(TAG, "issue with gettimg Posts",e);
                    return;
                }
//                for (classes course : courses){
//                    //Log.i(TAG, "Post:" + post.getDescription() + ", username: " + post.getUser().getUsername());
//
//                }
                allcourses.addAll(courses);
                adapter.notifyDataSetChanged();
                rvcourse.scheduleLayoutAnimation();
                Log.i(TAG,"classes size  " + allcourses.size());
            }

        });
    }





//        private String JsonDataFromAssets() {
//            String json = null;
//
//            try {
//                InputStream inputStream = getContext().getAssets().open("user.json");
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

        // 0. create a layout for one row in the list
        // 1.create the adapter
        // 2.create the adapter source
        // 3.set the adapter on the recycle view
        // 4. set the layout manager on the recycle view





}