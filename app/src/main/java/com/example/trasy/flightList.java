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
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

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

                String url = "https://partners.api.skyscanner.net/apiservices/v3/flights/indicative/search?market=UK&locale=en-GB&currency=GBP";

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\r\n  \"query\": {\r\n    \"currency\": \"GBP\",\r\n    \"locale\": \"en-GB\",\r\n    \"market\": \"UK\",\r\n    \"dateTimeGroupingType\": \"DATE_TIME_GROUPING_TYPE_BY_DATE\",\r\n    \"queryLegs\": [\r\n      {\r\n        \"originPlace\": {\r\n          \"queryPlace\": {\r\n           \r\n            \"iata\": \"LHR\"\r\n          }\r\n        },\r\n        \"destinationPlace\": {\r\n          \"queryPlace\": {\r\n            \r\n            \"iata\": \"CDG\"\r\n          }\r\n        },\r\n      \r\n        \"date_range\": {\r\n          \"startDate\": {\r\n            \"year\": 2023,\r\n            \"month\": 11\r\n          },\r\n          \"endDate\": {\r\n            \"year\": 2023,\r\n            \"month\": 11\r\n          }\r\n        }\r\n      }\r\n    ]\r\n  }\r\n}");
                Request request = new Request.Builder()
                        .url("https://partners.api.skyscanner.net/apiservices/v3/flights/indicative/search?market=UK&locale=en-GB&currency=GBP")
                        .post(body)
                        .addHeader("Accept", "*/*")
                        .addHeader("User-Agent", "Thunder Client (https://www.thunderclient.com)")
                        .addHeader("x-api-key", "prtl6749387986743898559646983194")
                        .addHeader("Content-Type", "application/json")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // Handle network error
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful() && response.body() != null) {

                            String json = response.body().string();
                            Gson gson = new Gson();
                            Flight flight = gson.fromJson(json, Flight.class);
                            runOnUiThread(() -> {
                                progressBar.setVisibility(View.VISIBLE);
                                theFlightList.add(flight);
                                depart.setText(flight.toString());
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
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
