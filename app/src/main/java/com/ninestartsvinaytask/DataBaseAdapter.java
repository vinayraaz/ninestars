package com.ninestartsvinaytask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ninestartsvinaytask.model.DatabaseModel;

import java.util.ArrayList;

import static android.provider.MediaStore.Images.Thumbnails.IMAGE_ID;

public class DataBaseAdapter {
    ArrayList<DatabaseModel> photoList = new ArrayList<DatabaseModel>();
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "MyDataBase";
    private static final String DATABASE_TABLE = "photoTable";

    public static final String KEY_ROWID = "_id";

    public static final String KEY_albumId = "albumId";
    public static final String KEY_id = "id";
    public static final String KEY_title = "title";
    public static final String KEY_url = "url";
    public static final String KEY_thumbnailUrl = "thumbnailUrl";


    private static final String DATABASE_CREATE =
            "create table photoTable (_id integer primary key autoincrement, albumId text not null,id text not null,"
                    + "title text not null,url text not null,thumbnailUrl text not null);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DataBaseAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }


    private class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS photoList");
            onCreate(db);

        }
    }
    public DataBaseAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //insert data
    public long insertData(String albumId,String id,String title,String url, String thumbnailUrl)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_albumId, albumId);
        initialValues.put(KEY_id, id);
        initialValues.put(KEY_title, title);
        initialValues.put(KEY_url, url);
        initialValues.put(KEY_thumbnailUrl, thumbnailUrl);
        System.out.println("Database inserted");
        return db.insert(DATABASE_TABLE, null, initialValues);
    }



    // retrive single data
    public Cursor getSingleData(long rowId) throws SQLException
    {
        Cursor mCursor =db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_albumId,KEY_id,KEY_title,KEY_url, KEY_thumbnailUrl},
                KEY_ROWID + "=" + rowId, null,null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    // retrive all data
    public Cursor getAllData()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_albumId,KEY_id,KEY_title,KEY_url,KEY_thumbnailUrl},
                null, null, null, null, null,null);
    }


    // delete data
    public boolean deleteData(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }


    // delete all data
    public void deleteAllData()
    {
        db.delete(DATABASE_TABLE, null,null) ;
    }

  /*  public Cursor CheckTable() {
        String count = "SELECT count(*) FROM photoTable";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {
            System.out.println("HAve Data********" + icount);
        }
        else {
            System.out.println("No Data**********" + icount);
        }
        return mcursor;
    }*/

    public ArrayList<DatabaseModel> getcartPro() {
        Cursor cursor = db.rawQuery("select * from " + DATABASE_TABLE, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    DatabaseModel addCartList = new DatabaseModel();
                    addCartList.setAlbumId(cursor.getInt(1));
                    addCartList.setId(cursor.getInt(2));
                    addCartList.setTitle(cursor.getString(3));
                    addCartList.setUrl(cursor.getString(4));
                    addCartList.setThumbnailUrl(cursor.getString(5));
                    photoList.add(addCartList);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            cursor.close();
        }
        return photoList;

    }
}
