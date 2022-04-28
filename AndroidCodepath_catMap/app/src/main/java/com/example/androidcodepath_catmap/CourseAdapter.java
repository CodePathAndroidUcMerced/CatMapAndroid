package com.example.androidcodepath_catmap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter <CourseAdapter.ViewHolder> {

    private Context context;
    public List<classes> courses;

    public CourseAdapter( Context context, List<classes> courses) {

        this.context = context;
        this.courses = courses;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder,int position){
        classes course = courses.get(position);
        holder.bind(course);

//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                Intent i = new Intent( HelperAdapter.this, ViewLabActivity.class);
////                startActivity(i);
//
//                    Toast.makeText(context, "Item clicked." + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
//                }
//            });

    }

    @Override
    public int getItemCount () {
        return courses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView crn;
        private TextView courseId;
        private TextView courseName;
        private TextView instructorName;
        private TextView days;
        private TextView hours;
        private TextView room;

        private Button btnAdd;
        private Button btnRemove;

        private CardView cvCourse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            crn = (TextView) itemView.findViewById(R.id.crn);
            courseId = (TextView) itemView.findViewById(R.id.courseId);
            courseName = (TextView) itemView.findViewById(R.id.courseName);
            instructorName = (TextView) itemView.findViewById(R.id.tvInstructorName);
            days = (TextView) itemView.findViewById(R.id.tvDays);
            hours = (TextView) itemView.findViewById(R.id.tvHours);
            room = (TextView) itemView.findViewById(R.id.tvRoom);
            btnAdd = (Button) itemView.findViewById(R.id.btnAdd);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);
            cvCourse = (CardView) itemView.findViewById(R.id.cvCourse);
        }

        public void bind(classes course) {

            crn.setText(course.getCrn());
            courseId.setText(course.getCourse_id());
            courseName.setText(course.getCourse_name());
            instructorName.setText(course.getInstructor());
            days.setText(course.getDays());
            hours.setText(course.getHours());
            room.setText(course.getRoom());

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ADD ARRAY TO DATABASE
                    ParseUser.getCurrentUser().addAllUnique("classList", Arrays.asList(course.getObjectId()));
                    ParseUser.getCurrentUser().saveInBackground();
                    btnRemove.setVisibility(View.VISIBLE);
                    btnAdd.setVisibility(View.GONE);
                }
            });

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ADD ARRAY TO DATABASE
                    ArrayList<String> classList = (ArrayList) ParseUser.getCurrentUser().get("classList");
                    classList.remove(course.getObjectId()); // remove from Back4App
                    ParseUser.getCurrentUser().put("classList", classList);
                    ParseUser.getCurrentUser().saveInBackground();
                    notifyDataSetChanged(); // update dataset
                    btnRemove.setVisibility(View.GONE);
                    if(FragmentManager.findFragment(view).toString().contains("Home")) {
                        courses.remove(course); // remove from RecyclerView
                    }
                }
            });

            cvCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Keev", "onClick: working");
                    if(FragmentManager.findFragment(view).toString().contains("Home")) {
                        if (btnRemove.getVisibility() == View.VISIBLE) {
                            btnRemove.setVisibility(View.GONE);
                        } else {
                            btnRemove.setVisibility(View.VISIBLE);
                        }
                    }
                    else if(FragmentManager.findFragment(view).toString().contains("Course")) {
                        if (btnAdd.getVisibility() == View.VISIBLE) {
                            btnAdd.setVisibility(View.GONE);
                        } else {
                            btnAdd.setVisibility(View.VISIBLE);
                        }
                    }

                }
            });

        }
    }

}

