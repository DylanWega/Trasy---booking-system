package com.example.trasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchHotel extends AppCompatActivity {
    // fields that will be displayed on search
    private String hotelName;
    private String hotelLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        Button searchHotels = findViewById(R.id.button_searchHotel);
        searchHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // field for OkHttpClient
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://airbnb13.p.rapidapi.com/search-location?location=Paris&checkin=2023-09-16&checkout=2023-09-17&adults=1&children=0&infants=0&pets=0&page=1&currency=USD")
                        .get()
                        .addHeader("Accept", "*/*")
                        .addHeader("User-Agent", "Thunder Client (https://www.thunderclient.com)")
                        .addHeader("X-RapidAPI-Key", "12efc02e68msh8a95a40a10aabf5p104c4bjsn49d4264a561d")
                        .addHeader("X-RapidAPI-Host", "airbnb13.p.rapidapi.com")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        JSONObject hotelJsonResponse;
                        try {
                            if (response.body() != null) {
                                hotelJsonResponse = new JSONObject(response.body().string());
                                System.out.println(hotelJsonResponse);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }
}