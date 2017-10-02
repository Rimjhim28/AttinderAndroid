package com.codingblocks.attendancetracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codingblocks.attendancetracker.models.Attendance;

import java.util.ArrayList;

/**
 * Created by HP on 02-10-2017.
 */

public class AttendanceAdapter extends ArrayAdapter<Attendance> {

    public AttendanceAdapter(Context context, ArrayList<Attendance> allAttendance){
        super(context,0,allAttendance);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Attendance attendance = getItem(position);

        TextView tvBatchName = (TextView) convertView.findViewById(R.id.textview_batch_name);
        TextView tvlectureNumber = (TextView) convertView.findViewById(R.id.textview_lecture_number);
        TextView tvPresentStudents = (TextView) convertView.findViewById(R.id.textview_present_students);
        TextView tvAbsentstudents = (TextView) convertView.findViewById(R.id.textview_absent_students);

        tvBatchName.setText(attendance.getBatchName());
        tvlectureNumber.setText("0");
        tvPresentStudents.setText(attendance.getPresentStudents());
        tvAbsentstudents.setText(attendance.getAbsentStudents());

        return convertView;
    }
}
