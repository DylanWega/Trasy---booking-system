package com.example.trasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trasy.data.HotelRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

        EditText hotelName = findViewById(R.id.editTextEnterHotel);
        EditText editTextCheckIn = findViewById(R.id.editTextCheckInDate);
        EditText editTextCheckOut = findViewById(R.id.editTextCheckOutDate);
        EditText adultNo = findViewById(R.id.editTextAdultNo);
        EditText roomAmt = findViewById(R.id.editTextRoomNo);
        EditText childrenNo = findViewById(R.id.editTextChildNo);
        Button searchHotels = findViewById(R.id.button_searchHotel);
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recyclerViewHotels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // set the layout manager in the recycler view
        recyclerView.setLayoutManager(linearLayoutManager);
        HotelRecyclerAdapter viewAdapter = new HotelRecyclerAdapter(this, HotelList);
        recyclerView.setAdapter(viewAdapter);
        searchHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //empty the view if there is already something displayed in it
                HotelList.clear();
                HotelList.addAll(HotelList);
                viewAdapter.notifyDataSetChanged();

                String location = hotelName.getText().toString();
                String checkIn = editTextCheckIn.getText().toString();
                String checkOut = editTextCheckOut.getText().toString();
                String numberOfAdults = adultNo.getText().toString();
                String numberOfRooms = roomAmt.getText().toString();
                String numberOfChildren = childrenNo.getText().toString();

                // field for OkHttpClient
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://airbnb13.p.rapidapi.com/search-location?location=" + location + "&checkin=2023-09-16&checkout=2023-09-17&adults="+ numberOfAdults +"&children=" + numberOfChildren +"&infants=0&pets=0&page=1&currency=USD")
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

                                for (int i = 0; i < results.length(); i++){
                                    Hotel aHotel2 = new Hotel();

                                    JSONObject firstHotelName = results.getJSONObject(i);
                                    String hotelName = firstHotelName.getString("name");
                                    JSONObject firstHotelPrice = results.getJSONObject(i).getJSONObject("price");
                                    String price = firstHotelPrice.getString("rate");
                                    aHotel2.setHotelName(hotelName);
                                    aHotel2.setPrice(price);
                                    HotelList.add(aHotel2);
                                }

                            } catch (JSONException e) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SearchHotel.this);
                                throw new RuntimeException(e);
                            }
                            // display the view on the main UI thread
                            // otherwise throws a CalledFromWrongThreadException
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    HotelList.add(aHotel);
                                    viewAdapter.notifyDataSetChanged();
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