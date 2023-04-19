package com.example.trasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//Create database
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    //create the table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table Customer(" +
                "lastname varchar," +
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

    void Insertdata(String lname, String fname, String pwd, String email, String dob, String passportNo ){
        //open the database
        SQLiteDatabase ss = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("lastname", lname);
        contentValues.put("firstname", fname);
        contentValues.put("password", pwd);
        contentValues.put("email", email);
        contentValues.put("dob", dob);
        contentValues.put("passportNO",passportNo);

        ss.insert("Customer",null,contentValues);
    }

    public static class SearchHotel extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_hotel);
        }
    }
}

