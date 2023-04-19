package com.example.trasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.lang.String;

import androidx.annotation.Nullable;

//Create database
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "TrasyDB.db", null, 1);
    }

    //create the table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table Customers(" +
                "lastname varchar PRIMARY KEY," +
                "firstname varchar," +
                "password varchar," +
                "email varchar," +
                "dob Date," +
                "passportNo varchar)");
    }
//update database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean Insertdata(String lname, String fname, String pwd, String email, String dob, String passportNo ){
        //open the database
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("lastname", lname);
        contentValues.put("firstname", fname);
        contentValues.put("password", pwd);
        contentValues.put("email", email);
        contentValues.put("dob", dob);
        contentValues.put("passportNO",passportNo);

        myDB.insert("Customers",null,contentValues);
        long result = myDB.insert("Customers",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public boolean checkEmail (String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from Customers where email = ?", new String[]{email});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkEmailnPassword(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from Customers where email = ? and password = ?", new String[]{email, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

