package com.codingblocks.attendancetracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * Created by HP on 28-09-2017.
 */

public class DbHelper extends SQLiteOpenHelper{

    private static final String TAG = "DbHelper";
    private static final String DATABASE_NAME = "batches.db";
    private static final int DATABASE_VERSION = 3;

    //creating table for names of all batches
    public static final String TABLE_NAME_BATCH = "batch";
    public static final String COLUMN_BATCH_ID = "_id";
    public static final String COLUMN_BATCH_NAME = "batch";
    public static final String COLUMN_NUMBER_OF_LECTURES = "lectures";

    //creating table for names of students
    private static final String TABLE_NAME_STUDENTS = "students";
    private static final String COLUMN_STUDENT_ID = "_id";
    private static final String COLUMN_STUDENT_NAME = "name";
    private static final String COLUMN_STUDENT_ATTENDANCE = "attendance";
    private static final String COLUMN_STUDENT_BATCH_NAME = "batch_name";

    //creating the sql tab;e for batches
    private static final String SQL_CREATE_TABLE_BATCH = "CREATE TABLE " + TABLE_NAME_BATCH + "("
            + COLUMN_BATCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BATCH_NAME + " TEXT NOT NULL, "
            + COLUMN_NUMBER_OF_LECTURES + " REAL NOT NULL);";

    private static final String SQL_CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_NAME_STUDENTS + "("
            + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STUDENT_NAME + " TEXT NOT NULL, "
            + COLUMN_STUDENT_BATCH_NAME + " TEXT NOT NULL);";

    public DbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_BATCH);
        db.execSQL(SQL_CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_BATCH);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_STUDENTS);
        onCreate(db);
    }
}