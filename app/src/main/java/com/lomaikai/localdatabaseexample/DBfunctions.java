package com.lomaikai.localdatabaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBfunctions extends SQLiteOpenHelper {

    // Constructor which specifies the database name
    public DBfunctions(@Nullable Context context) {
        super(context,"Userdata.db", null, 1);
    }

    // Impleneting the onCreate and onUpgrade methods
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL query to create table called UserDetails
        db.execSQL("CREATE TABLE UserDetails(name TEXT primary key, contact TEXT, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // SQL query to replace the table if it previously existed
        db.execSQL("DROP TABLE IF EXISTS UserDetails");
    }

    // Create functions for each of the buttons
    // insert
    public Boolean insertUserData(String name, String contact, String dob) {
        // Create database object and get a writable one
        SQLiteDatabase db = this.getWritableDatabase();
        // Create contentValues object that we can use to put values into our table
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        // Insert the data into the table
        long result = db.insert("UserDetails",null,contentValues);
        // if result == -1, insertion failed and will return false and insert unsuccessful
        if (result == -1)
            return false;
        else
            return true;
    }

    // update
    public Boolean updateUserData(String name, String contact, String dob) {
        // Create database object and get a writable one
        SQLiteDatabase db = this.getWritableDatabase();
        // Create contentValues object that we can use to put values into our table
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        // Check if the content exists already or not
        Cursor cursor = db.rawQuery("SELECT * FROM UserDetails WHERE NAME = ?", new String[]{name});
        // Check if the cursor has any data, then only can we update
        if (cursor.getCount()>0) {
            // We will only update the databse if the name that has passed matches the one passed into the database
            long result = db.update("UserDetails", contentValues, "name=?", new String[]{name});
            if (result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    // delete
    public Boolean deleteUserData(String name) {
        // Create database object and get a writable one
        SQLiteDatabase db = this.getWritableDatabase();
        // Check if the content exists already or not
        Cursor cursor = db.rawQuery("SELECT * FROM UserDetails WHERE NAME = ?", new String[]{name});
        // Check if the cursor has any data, then only can we update
        if (cursor.getCount()>0) {
            // We will only update the databse if the name that has passed matches the one passed into the database
            long result = db.delete("UserDetails","name=?", new String[]{name});
            if (result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }
}
