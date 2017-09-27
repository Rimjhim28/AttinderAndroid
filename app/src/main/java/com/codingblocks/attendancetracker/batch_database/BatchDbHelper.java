package com.codingblocks.attendancetracker.batch_database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 18-09-2017.
 */

public class BatchDbHelper extends SQLiteOpenHelper {

    private  static final String LOG_TAG = BatchDbHelper.class.getName();

    private  static final String DATA_BASE_NAME = "attendance.db";

    private  static final int DATA_BASE_VERSION = 1;
    private ArrayList<String> allBatches;


    public BatchDbHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_BATCH_TABLE =  "CREATE TABLE " + BatchContract.BatchEntry.TABLE_NAME + " ("
                + BatchContract.BatchEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BatchContract.BatchEntry.COLUMN_BATCH_NAME + " TEXT NOT NULL, "
                + BatchContract.BatchEntry.COLUMN_NUMBER_OF_LECTURES + " INTEGER NOT NULL);";
        db.execSQL(SQL_CREATE_BATCH_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> getAllBatches() {
        List<String> batches = new ArrayList<>();
        String query = "SELECT  * FROM "+ BatchContract.BatchEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                batches.add(cursor.getString(cursor.getColumnIndex(BatchContract.BatchEntry.COLUMN_BATCH_NAME)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return (ArrayList<String>) batches;
    }
}
