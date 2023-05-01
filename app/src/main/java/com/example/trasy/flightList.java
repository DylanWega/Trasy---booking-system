package com.example.trasy;

import  androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.trasy.R;
import com.example.trasy.data.RetrofitClient;
import com.example.trasy.data.postAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import Model.Flight;

import okhttp3.*;
import okhttp3.Call;
import okhttp3.Response;

public class flightList extends AppCompatActivity {

    OkHttpClient client;
    private Button searchFlight;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    EditText depart, arrive, departDate, returnDate;
    LinearLayoutManager layoutManager;
    postAdapter adapter;
    List<Flight> theFlightList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        //client = new OkHttpClient();

        depart = (EditText) findViewById(R.id.txtDeparture);
        arrive = (EditText) findViewById(R.id.txtDestination);
        departDate = (EditText) findViewById(R.id.txtdepartDate);
        returnDate = (EditText) findViewById(R.id.txtreturnDate);

        searchFlight = (Button) findViewById(R.id.searchBtn);
        recyclerView = (RecyclerView) findViewById(R.id.reyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);

        //set the layout manager on the t recycler view
        recyclerView.setLayoutManager(layoutManager);
        adapter = new postAdapter(theFlightList);
        recyclerView.setAdapter(adapter);


        searchFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String departure = depart.getText().toString();
                String arrival = arrive.getText().toString();
                String departureDate = departDate.getText().toString();
                String returningDate = returnDate.getText().toString();

                String url = "https://skyscanner44.p.rapidapi.com/fly-to-country?"
                        + "destination=" + arrival
                        + "&origin=" + departure
                        + "&departureDate=" + departureDate
                        + "&returnDate=" + returningDate
                        + "&currency=EUR"
                        + "&locale=en-GB"
                        + "&country=UK";

                // Create an HTTP request for the API endpoint
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .addHeader("content-type", "application/octet-stream")
                        .addHeader("X-RapidAPI-Key", "21393f3b8bmsh620bcf0dfdd46e3p1d3dc6jsnff75302c6049")
                        .addHeader("X-RapidAPI-Host", "skyscanner44.p.rapidapi.com")
                        .build();


                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (response.isSuccessful() && response.body() != null) {
                                            progressBar.setVisibility(View.VISIBLE);
                                            String responseBody = null;
                                            try {
                                                responseBody = response.body().string();
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            List<Flight> flights = new Gson().fromJson(responseBody, new TypeToken<List<Flight>>(){}.getType());
                                            theFlightList.addAll(flights);
                                            adapter.notifyDataSetChanged();
                                            progressBar.setVisibility(View.GONE);
                                        }

                                    }
                                });
                        }
                    });
                    }
                });

    }

}