package com.codingblocks.attendancetracker.batch_database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by HP on 18-09-2017.
 */

public class BatchContract {

    public static final String CONTENT_AUTHORITY = "com.codingblocks.attendancetracker";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_BATCHES = "batches";

    public static abstract class BatchEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_BATCHES);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BATCHES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +PATH_BATCHES;

        public static final String TABLE_NAME = "batches";
        public static final String _ID = "_id";
        public static final String COLUMN_BATCH_NAME = "name";
        public static final String COLUMN_NUMBER_OF_LECTURES = "lectures";

    }
}
