package com.example.trasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.lang.String;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.Nullable;

import com.example.trasy.data.postAdapter;

//Create database
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "TrasyDB.db", null, 1);
    }


    //create the customer table
    @Override
    //create customer table
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table Customers(" +
                "lastname varchar ," +
                "firstname varchar," +
                "password varchar," +
                "email varchar PRIMARY KEY ," +
                "dob Date," +
                "passportNo varchar)");

        //create Flight table
        sqLiteDatabase.execSQL("Create Table Flight(" +
                "flightNo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "departureCountry varchar," +
                "destinationCountry varchar," +
                "email varchar ," +
                "price varchar ," +
                "FOREIGN KEY(email) REFERENCES Customers(email))");

        //create Hotel table
        sqLiteDatabase.execSQL("Create Table Hotel(" +
                "hotelId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hotelName varchar," +
                "hotelPrice varchar ," +
                "email varchar ," +
                "FOREIGN KEY(email) REFERENCES Customers(email))");

        //create booking table
        sqLiteDatabase.execSQL("Create Table Bookings(" +
                "serviceID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email varchar," +
                "FOREIGN KEY(email) REFERENCES Customers(email))");
    }

//update database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public static String[] breakThisDate(String dateString)  {
       String[] parts = dateString.split("/");

        return new String[] {parts[0], parts[1], parts[2]};

    }
    //insert flight info
    public boolean InsertFlight(String depCountry, String destCountry, String emailAd, String price){
        //open the database
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("departureCountry", depCountry);
        contentValues.put("destinationCountry", destCountry);
        contentValues.put("email", emailAd);
        contentValues.put("price", price);

        myDB.insert("Flight",null,contentValues);
        long result = myDB.insert("Flight",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    //insert hotel info
    public boolean InsertHotel(String hotelName, String emailAd, String hotelPrice){
        //open the database
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hotelName", hotelName);
        contentValues.put("email", emailAd);
        contentValues.put("hotelPrice", hotelPrice);

        myDB.insert("Hotel",null,contentValues);
        long result = myDB.insert("Hotel",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    //insert Bookings
    public boolean InsertBookings(String emailBook){
        //open the database
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", emailBook);


        myDB.insert("Bookings",null,contentValues);
        long result = myDB.insert("Bookings",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    //Insert customer
    public boolean Insertdata(String lname, String fname, String pwd, String email, String dob, String passportNo ){
        //open the database
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("lastname", lname);
        contentValues.put("firstname", fname);
        contentValues.put("password", pwd);
        contentValues.put("email", email);
        contentValues.put("dob", dob);
        contentValues.put("passportNo",passportNo);

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

