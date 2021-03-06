package com.codingblocks.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codingblocks.attendancetracker.database.AttendanceDAO;
import com.codingblocks.attendancetracker.database.StudentsDAO;
import com.codingblocks.attendancetracker.models.Student;

import java.util.ArrayList;

public class ListOfAbsentPresentStudentsActivity extends AppCompatActivity {

    private Button submitBtn;
    private RecyclerView presentList, absentList;

    private ArrayList<Student> presentStudents, absentStudents;

    private PresentAdapter presentAdapter;
    private AbsentAdapter absentAdapter;
    private ArrayList<Integer> presentIds;
    private ArrayList<Integer> absentIds;
    private String batch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_absent_present_students);

        FontsOverride.applyFontForToolbarTitle(this, FontsOverride.FONT_PROXIMA_NOVA,getWindow());

        Intent intent = getIntent();
        fetchStudents(intent);
        init();
    }

    private void fetchStudents(Intent intent) {

        presentIds = intent.getIntegerArrayListExtra("presentIds");
        absentIds = intent.getIntegerArrayListExtra("absentIds");
        batch = intent.getStringExtra("batchName");

        ArrayList<Student> allStudents = new StudentsDAO(this).getallStudents(batch);

        presentStudents = new ArrayList<>();
        absentStudents = new ArrayList<>();

        for (int i = 0; i < allStudents.size(); i++) {
            for (int j = 0; j < presentIds.size(); j++) {
                if (presentIds.get(j) == allStudents.get(i).getUniqueId()) {
                    presentStudents.add(allStudents.get(i));
                }
            }
        }

        for (int i = 0; i < allStudents.size(); i++) {
            for (int j = 0; j < absentIds.size(); j++) {
                if (absentIds.get(j) == allStudents.get(i).getUniqueId()) {
                    absentStudents.add(allStudents.get(i));
                }
            }
        }
    }

    private void init() {
        presentList = (RecyclerView) findViewById(R.id.list_present);
        absentList = (RecyclerView) findViewById(R.id.list_absent);
        submitBtn = (Button) findViewById(R.id.list_item_students_btn_submit);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListOfAbsentPresentStudentsActivity.this, R.string.toast_attendance_is_saved, Toast.LENGTH_SHORT).show();
                submitAttendance();
            }
        });

        presentAdapter = new PresentAdapter();
        absentAdapter = new AbsentAdapter();

        presentList.setAdapter(presentAdapter);
        presentList.setLayoutManager(new LinearLayoutManager(this));

        absentList.setAdapter(absentAdapter);
        absentList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void submitAttendance(){
        AttendanceDAO ob = new AttendanceDAO(this);
        long id = ob.addAttendance(batch,0,presentIds,absentIds);
        Toast.makeText(this,""+id,Toast.LENGTH_SHORT).show();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;

        MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class PresentAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = getLayoutInflater().inflate(R.layout.list_item_absent_present_students, null);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            myViewHolder.tv_name = (TextView) view.findViewById(R.id.list_item_student_names);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.tv_name.setText(presentStudents.get(position).getName());
            holder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    absentStudents.add(presentStudents.get(holder.getAdapterPosition()));
                    presentStudents.remove(presentStudents.get(holder.getAdapterPosition()));

                    presentAdapter.notifyDataSetChanged();
                    absentAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return presentStudents.size();
        }
    }

    private class AbsentAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = getLayoutInflater().inflate(R.layout.list_item_absent_present_students, null);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            myViewHolder.tv_name = (TextView) view.findViewById(R.id.list_item_student_names);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.tv_name.setText(absentStudents.get(position).getName());
            holder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presentStudents.add(absentStudents.get(holder.getAdapterPosition()));
                    absentStudents.remove(absentStudents.get(holder.getAdapterPosition()));

                    presentAdapter.notifyDataSetChanged();
                    absentAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return absentStudents.size();
        }

    }

}
