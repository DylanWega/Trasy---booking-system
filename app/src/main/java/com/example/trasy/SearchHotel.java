package com.example.trasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.trasy.data.HotelRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Hotel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchHotel extends AppCompatActivity {
    // fields that will be displayed on search
    private String hotelName;
    private String hotelLocation;
    private final List<Hotel> HotelList = new ArrayList<>();
    Hotel aHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        Button searchHotels = findViewById(R.id.button_searchHotel);
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recyclerViewHotels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // set the layout manager in the recycler view
        recyclerView.setLayoutManager(linearLayoutManager);
        HotelRecyclerAdapter viewAdapter = new HotelRecyclerAdapter(HotelList);
        recyclerView.setAdapter(viewAdapter);
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
                        // field to hold the json format of the response
                        JSONObject hotelJsonResponse;
                        // will not work without try/catch
                        if (response.body() != null) {
                            try {

                                hotelJsonResponse = new JSONObject(response.body().string());
                                System.out.println(hotelJsonResponse);
                                aHotel = new Hotel();
                                JSONArray results = hotelJsonResponse.getJSONArray("results");
                                JSONObject firstHotel = results.getJSONObject(0);
                                String hotelName = firstHotel.getString("name");
                                aHotel.setHotelName(hotelName);

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            // display the view on the main UI thread
                            // otherwise throws a CalledFromWrongThreadException
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    HotelList.add(aHotel);
                                    viewAdapter.notifyDataSetChanged();
//                                System.out.println("Is it fetching the hotel name:" + aHotel.getHotelName());
                                    if (aHotel.getHotelName().equals("")) {
                                        Toast.makeText(SearchHotel.this, "Not getting the hotel name", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SearchHotel.this, "Gets hotel name", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            // Handle unsuccessful response
                            System.out.println("Unsuccessful response: " + response.code());
                        }
                    }
                });
            }
        });
    }
}