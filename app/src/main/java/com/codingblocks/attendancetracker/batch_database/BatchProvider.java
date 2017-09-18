package com.codingblocks.attendancetracker.batch_database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codingblocks.attendancetracker.models.Batch;

import static java.security.AccessController.getContext;

/**
 * Created by HP on 18-09-2017.
 */

public class BatchProvider extends ContentProvider {

    //DataBase helper object
    private BatchDbHelper mDbHelper;

    public static final String LOG_TAG = BatchProvider.class.getSimpleName();

    //Uri matcher code for whole TAble
    private static final int BATCHES = 100;

    //Uri matcher code for a specific row
    private static final int BATCHES_ID = 101;

    //Setup UriMatcher Object
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //Setting up the UriMatcher variables
    static{
        sUriMatcher.addURI(BatchContract.CONTENT_AUTHORITY,BatchContract.PATH_BATCHES,BATCHES);
        sUriMatcher.addURI(BatchContract.CONTENT_AUTHORITY,BatchContract.PATH_BATCHES + "/#",BATCHES_ID);
    }

    /*
    Initialize the provider and DataBase
     */
    @Override
    public boolean onCreate() {
        mDbHelper = new BatchDbHelper(getContext());
        return false;
    }

    /*
    Perform the query for the given Uri Use the given projection, selection, se;ection arguments and sort order
     */

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        //Get Readable DataBase
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        //This cursor will hold the result of the Query
        Cursor cursor = null;

        //Figure out if the given Uri matches with the coded Integer values
        int match = sUriMatcher.match(uri);

        //Performing the specific task depending on the value of match
        switch (match){
            case BATCHES:
                //for BATCHES query the whole table Directly
                cursor = database.query(BatchContract.BatchEntry.TABLE_NAME,projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;

            case BATCHES_ID:
                //for specific columns of the table
                selection = BatchContract.BatchEntry._ID+"=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(BatchContract.BatchEntry.TABLE_NAME,projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cannot query unknown URI"+uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    /*
    Inserts new data into the provider with the given Content values
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        final int match = sUriMatcher.match(uri);
        switch (match){
            case BATCHES:
                return insertBatch(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for "+uri);
        }
    }

    /*
    insertPet() to insert a particular column
     */

    private Uri insertBatch(Uri uri, ContentValues values) {

        //Check if the name is not null
        String name = values.getAsString(BatchContract.BatchEntry.COLUMN_BATCH_NAME);
        if (name == null)
            throw new IllegalArgumentException("Batch requires a Name");

        //Check if number of lectures is not negetive
        Integer lectures = values.getAsInteger(BatchContract.BatchEntry.COLUMN_NUMBER_OF_LECTURES);
        if (lectures < 0 && lectures != null)
            throw new IllegalArgumentException("Number of Lectures should be positive");


        //Get a writable DataBase
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        //Insert the database
        long id = database.insert(BatchContract.BatchEntry.TABLE_NAME, null, values);

        if (id == -1){
            Log.e(LOG_TAG, "Failed to Insert the Data " + uri);
            return null;
        }

        //Notify all the listeners that the data has changed
        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri,id);
    }


    /*
    Delete the Data at the given selection and selection arguments
     */

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /*
        Updates the data at the given selection and selection arguments with the given content values
         */

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /*
    Return the MIME type of the data for the content URI
     */

    @Override
    public String getType( Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case BATCHES:
                return BatchContract.BatchEntry.CONTENT_LIST_TYPE;
            case BATCHES_ID:
                return BatchContract.BatchEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown uri " + uri + "with match " +match);
        }
    }
}
