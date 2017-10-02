package com.codingblocks.attendancetracker.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codingblocks.attendancetracker.models.Attendance;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.codingblocks.attendancetracker.R.string.absent;

/**
 * Created by HP on 01-10-2017.
 */

public class AttendanceDAO {

    Context mContext;
    DbHelper mDbHelper;
    SQLiteDatabase database;

    public AttendanceDAO(Context context){
        mContext = context;
        mDbHelper = new DbHelper(mContext);
    }

    public long addAttendance(String batch, int lectureNumber, ArrayList<Integer> presentStudents,ArrayList<Integer> absentStudents){

        String present="",absent = "";
        for(int presentees : presentStudents)
            present = present + "\t" + presentees;
        for(int absentees : absentStudents)
            absent = absent + "\t" +absentees;
        database = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_BATCH,batch);
        values.put(DbHelper.COLUMN_LECTURE_NUMBER,lectureNumber);
        values.put(DbHelper.COLUMN_STUDENTS_PRESENT,present);
        values.put(DbHelper.COLUMN_STUDENT_ABSENT,absent);
        long id = database.insert(DbHelper.TABLE_NAME_ATTENDANCE,null,values);
        return id;
    }

    public List<Attendance> getAllAttendance(){
        Attendance attendance;
        List<Attendance> allAttendance = new ArrayList<>();
        database = mDbHelper.getReadableDatabase();
        Cursor cursor = database.query(DbHelper.TABLE_NAME_ATTENDANCE,null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                String batchName = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_BATCH));
                int lectureNumber = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_LECTURE_NUMBER));
                String presentStudents = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_STUDENTS_PRESENT));
                String absentStudents = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_STUDENT_ABSENT));
                attendance = new Attendance(batchName,lectureNumber,presentStudents,absentStudents);
                allAttendance.add(attendance);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return allAttendance;
    }
}
