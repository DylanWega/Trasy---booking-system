package com.example.trasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.trasy.data.SessionManager;

public class Homepage extends AppCompatActivity {
    Button bookFlightBtn, bookHotelBtn, bookCarBtn, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        bookFlightBtn = (Button) findViewById(R.id.bookFlightBtn);
        logout = (Button) findViewById(R.id.logoutBtn);
        SessionManager session = new SessionManager(getApplicationContext());


        bookFlightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), flightList.class);
                startActivity(intent);
            }
        });

        //logout button on click event
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.isLoggedIn() == true){
                    session.logout();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}