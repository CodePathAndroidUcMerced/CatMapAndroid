package com.example.androidcodepath_catmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.ArrayList;
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
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType)
    {
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

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                crn = (TextView) itemView.findViewById(R.id.crn);
                courseId = (TextView) itemView.findViewById(R.id.courseId);
                courseName = (TextView) itemView.findViewById(R.id.courseName);
            }

            public void bind(classes course) {

                crn.setText(course.getCrn());
                courseId.setText(course.getCourse_id());
                courseName.setText(course.getCourse_name());
            }
        }

}

