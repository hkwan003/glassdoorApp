package com.example.calvinkwan.glassdoor_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by calvinkwan on 4/1/16.
 */
public class DatabaseAdapter
{
    //Context context;
    DBHelper helper;
    public DatabaseAdapter(Context context)
    {
        helper = new DBHelper(context);
    }
    public long insertData(String name)                     //made separate function to push data into private variables
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, name);             //initalize it as name schema
        long id = db.insert(DBHelper.TABLE_NAME, null, contentValues);      //ID returns negative if something went wrog
        return id;                                          //else returns ID of successful insertion of row number
    }

    public String getAllData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] columns = {DBHelper.UID, DBHelper.NAME};
        Cursor cursor = db.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext())
        {
            int cid = cursor.getInt(0);
            String name = cursor.getString(1);
            buffer.append(cid+" "+name + "\n");
        }
        return buffer.toString();
    }

    public String getData(String name)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] columns = {DBHelper.NAME};
        Cursor cursor = db.query(DBHelper.TABLE_NAME, columns, DBHelper.NAME+" = '"+name+"'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext())
        {
            int index1 = cursor.getColumnIndex(DBHelper.NAME);
            String Personname = cursor.getString(index1);
            buffer.append(Personname + "\n");
        }
        return buffer.toString();
    }


    public int deleteRow(String UserToDelete)
    {
        //Delete * from CalvTable where name = "calvib"
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs = {UserToDelete};
        int count = db.delete(DBHelper.TABLE_NAME, DBHelper.NAME+ "=?", whereArgs);
        return count;
    }

    static public class DBHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "CALVDB";           //database schehema
        private static final String TABLE_NAME = "CALVTABLE";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
                + " SQLITE_MAX_LENGTH);";

        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;


        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME , null, DATABASE_VERSION);        //1 is database version number
            this.context = context;

            Message.message(context, "constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try
            {
                db.execSQL(CREATE_TABLE);
                Message.message(context, "onCreate is Called");
            }
            catch (SQLException e)
            {
                Message.message(context, ""+e);
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVesion, int newVersion)
        {
            try
            {
                Message.message(context, "onUpgrade is called");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }
            catch (Exception e)
            {
                Message.message(context, ""+e);
            }
        }
    }

}
