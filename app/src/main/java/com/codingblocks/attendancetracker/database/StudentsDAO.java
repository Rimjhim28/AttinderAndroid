package com.codingblocks.attendancetracker.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by HP on 01-10-2017.
 */

public class StudentsDAO {

    Context mContext;
    DbHelper mDbHelper;

    public StudentsDAO(Context context){
        mContext = context;
        mDbHelper = new DbHelper(mContext);
    }

    public long createStudent(String studentName, String batchName){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_STUDENT_NAME,studentName);
        values.put(DbHelper.COLUMN_STUDENT_BATCH_NAME,batchName);
        long id = database.insert(DbHelper.TABLE_NAME_STUDENTS,null,values);
        return id;
    }
}
