package com.example.trasy.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "pwd";
    private static final String KEY_LNAME = "lastname";
    private static final String KEY_FNAME = "firstname";
    private static final String KEY_LOGGED_IN = "logged_in";
    private static final String PREF_NAME = "session";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }
    //user's parameters stored in a session to be called later on
    public void setLoggedIn(String KEY_EMAIL, String email) {
        editor.putString(KEY_EMAIL, email);
       // editor.putString(KEY_PASSWORD, pwd);
        //editor.putString(KEY_LNAME, lastname);
        //editor.putString(KEY_FNAME, firstname);
        //editor.putBoolean(KEY_LOGGED_IN, true);
        editor.apply();
    }
    //to check if user is logged in
    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_LOGGED_IN, false);
    }
    //to logout the user
    public void logout() {
        editor.clear();
        editor.apply();
    }

    public String getEmail(String KEY_EMAIL) {
        return pref.getString(KEY_EMAIL, "");
    }

    public String getPassword() {
        return pref.getString(KEY_PASSWORD, null);
    }
}

