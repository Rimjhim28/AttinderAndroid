package com.codingblocks.attendancetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.codingblocks.attendancetracker.database.AttendanceDAO;
import com.codingblocks.attendancetracker.models.Attendance;

import java.util.ArrayList;

public class AttendanceDatabaseActivity extends AppCompatActivity {

    ListView attendanceListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_database);
        attendanceListView = (ListView) findViewById(R.id.listview_attendance_database);
        getAllAttendance();
    }

    private void getAllAttendance(){
        AttendanceDAO ob = new AttendanceDAO(this);
        ArrayList<Attendance> allAttendance = (ArrayList<Attendance>) ob.getAllAttendance();
        AttendanceAdapter myAdapter = new AttendanceAdapter(this,allAttendance);
        attendanceListView.setAdapter(myAdapter);
    }
}
