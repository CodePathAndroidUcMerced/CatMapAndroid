package com.example.androidcodepath_catmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter <CourseAdapter.ViewHolder> {

    Context context;
    ArrayList<String> crn;
    ArrayList<String> courseId;
    ArrayList<String> courseName;

    public CourseAdapter(ArrayList<String> crn, ArrayList<String> courseId, ArrayList<String> courseName, Context context) {
        this.crn = crn;
        this.courseId = courseId;
        this.courseName = courseName;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
            View view = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder,int position){
            holder.crn.setText(crn.get(position));
            holder.courseId.setText(courseId.get(position));
            holder.courseName.setText(courseName.get(position));

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
            return courseName.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView crn;
            TextView courseId;
            TextView courseName;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                crn = (TextView) itemView.findViewById(R.id.crn);
                courseId = (TextView) itemView.findViewById(R.id.courseId);
                courseName = (TextView) itemView.findViewById(R.id.courseName);
            }

        }

}

