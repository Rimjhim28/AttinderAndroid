package com.codingblocks.attendancetracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.codingblocks.attendancetracker.CreateNewCourse;
import com.codingblocks.attendancetracker.models.Batch;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by HP on 28-09-2017.
 */

public class BatchesDAO {
    Context mContext;
    DbHelper mD;
    public BatchesDAO(Context context){
        mContext = context;
        mD = new DbHelper(mContext);
    }

    public long createBatch(String name){
        SQLiteDatabase database = mD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_BATCH_NAME,name);
        long id = database.insert(DbHelper.TABLE_NAME_BATCH,null,values);
        return id;
    }

    public List<String> getAllBatches(){
        List<String> batches = new ArrayList<>();
        SQLiteDatabase database = mD.getReadableDatabase();
        Cursor cursor = database.query(DbHelper.TABLE_NAME_BATCH,new String[]{DbHelper.COLUMN_BATCH_NAME},null,null,null,null,null);
        cursor.moveToFirst();
        do{
        int nameColumnIndex = cursor.getColumnIndex(DbHelper.COLUMN_BATCH_NAME);
        String batchName = cursor.getString(nameColumnIndex);
        batches.add(batchName);
        }while (cursor.moveToNext());
        return batches;
    }
}
