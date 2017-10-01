package com.codingblocks.attendancetracker.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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
            present = present + "\n" + presentees;
        for(int absentees : absentStudents)
            absent = absent + "\n" +absentees;
        database = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_BATCH,batch);
        values.put(DbHelper.COLUMN_LECTURE_NUMBER,lectureNumber);
        values.put(DbHelper.COLUMN_STUDENTS_PRESENT,present);
        values.put(DbHelper.COLUMN_STUDENT_ABSENT,absent);
        long id = database.insert(DbHelper.TABLE_NAME_ATTENDANCE,null,values);
        return id;
    }
}
