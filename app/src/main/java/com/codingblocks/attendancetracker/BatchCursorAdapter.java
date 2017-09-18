package com.codingblocks.attendancetracker;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.codingblocks.attendancetracker.batch_database.BatchContract;

/**
 * Created by HP on 18-09-2017.
 */

public class BatchCursorAdapter extends CursorAdapter {

    public BatchCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_spinner,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView textViewBatchName = (TextView) view.findViewById(R.id.text_batch_name);

        int idBatchName = cursor.getColumnIndex(BatchContract.BatchEntry.COLUMN_BATCH_NAME);
        String batchName = cursor.getString(idBatchName);

        textViewBatchName.setText(batchName);
    }
}
