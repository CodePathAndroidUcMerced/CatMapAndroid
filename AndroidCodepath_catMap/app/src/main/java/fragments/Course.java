package fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidcodepath_catmap.CourseAdapter;
import com.example.androidcodepath_catmap.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Course#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Course extends Fragment {

    ArrayList<String> crn = new ArrayList<>();
    ArrayList<String> courseId = new ArrayList<>();
    ArrayList<String> courseName = new ArrayList<>();

    private RecyclerView rvcourse;
    protected CourseAdapter adapter;




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


        // json object
        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAssets());
            JSONArray jsonArray = jsonObject.getJSONArray("fall2020");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userData = jsonArray.getJSONObject(i);
                if (userData.getString("type").equals("LECT")) {
                    crn.add(userData.getString("crn"));
                    courseId.add(userData.getString("course_id"));
                    courseName.add(userData.getString("course_name"));
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new CourseAdapter(crn, courseId,courseName,getContext());

        rvcourse.setAdapter(adapter);
        rvcourse.setLayoutManager(new LinearLayoutManager(getContext()));

    }



        private String JsonDataFromAssets() {
            String json = null;

            try {
                InputStream inputStream = getContext().getAssets().open("user.json");

                int sizeofFile = inputStream.available();
                byte[] bufferData = new byte[sizeofFile];

                inputStream.read(bufferData);
                inputStream.close();

                json = new String(bufferData, "UTF-8");

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            return json;
        }

        // 0. create a layout for one row in the list
        // 1.create the adapter
        // 2.create the adapter source
        // 3.set the adapter on the recycle view
        // 4. set the layout manager on the recycle view





}