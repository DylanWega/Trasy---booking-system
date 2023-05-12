package com.example.trasy;

import static com.example.trasy.DatabaseHelper.breakThisDate;

import  androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import Model.Flight;

import okhttp3.*;
import okhttp3.Call;
import okhttp3.Response;

public class flightList extends AppCompatActivity {

    OkHttpClient client;
    private Button searchFlight;
    RecyclerView recyclerview;
    ProgressBar progressBar;
    EditText depart, arrive, departDate, returnDate;
    //LinearLayoutManager layoutManager;
    postAdapter adapter;
    List<Flight> theFlightList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        //client = new OkHttpClient();
        //declaring UI view id
        depart = (EditText) findViewById(R.id.txtDeparture);
        arrive = (EditText) findViewById(R.id.txtDestination);
        departDate = (EditText) findViewById(R.id.txtdepartDate);

        searchFlight = (Button) findViewById(R.id.searchBtn);
        recyclerview = (RecyclerView) findViewById(R.id.reyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        adapter = new postAdapter(this,theFlightList);
        //set the layout manager on the t recycler view
        //recyclerview.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        //Here with the aid of constructor, I store info about the flight and departure date.
        //This information will help me later to create the details of the booking

        recyclerview.setAdapter(adapter);


        searchFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //empty the view if there is already something displayed in it
                theFlightList.clear();
                theFlightList.addAll(theFlightList);
                adapter.notifyDataSetChanged();

                String departure = depart.getText().toString();
                String arrival = arrive.getText().toString();
                String departureDate = departDate.getText().toString();


                //Here we call the method defined in the DatabaseHelper to break the date into parts
                String[] dateParts = breakThisDate(departureDate);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                //String returningDate = returnDate.getText().toString();

                String url = "https://partners.api.skyscanner.net/apiservices/v3/flights/indicative/search?market=UK&locale=en-GB&currency=GBP";

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\r\n  \"query\": {\r\n    \"currency\": \"GBP\",\r\n    \"locale\": \"en-GB\",\r\n    \"market\": \"UK\",\r\n    \"dateTimeGroupingType\": \"DATE_TIME_GROUPING_TYPE_BY_DATE\",\r\n    \"queryLegs\": [\r\n      {\r\n        \"originPlace\": {\r\n          \"queryPlace\": {\r\n           \r\n            \"iata\": \""+departure+"\"\r\n          }\r\n        },\r\n        \"destinationPlace\": {\r\n          \"queryPlace\": {\r\n            \r\n            \"iata\": \""+arrival+"\"\r\n          }\r\n        },\r\n      \r\n        \"date_range\": {\r\n          \"startDate\": {\r\n            \"year\": "+year+",\r\n            \"month\": "+month+"\r\n          },\r\n          \"endDate\": {\r\n            \"year\": "+year+",\r\n            \"month\": "+month+"\r\n          }\r\n        }\r\n      }\r\n    ]\r\n  }\r\n}");                Request request = new Request.Builder()
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
                            //flight object
                            Flight flight;
                            //JSON object to hold the response results
                            JSONObject jsonResponse;
                            // conversion will not work without try catch
                            try {
                                // convert response results to JSON object literal
                                jsonResponse = new JSONObject(response.body().string());
                                //String jsonString = response.body().string();
                                //JSONArray jsonArray = new JSONArray(jsonResponse);
                                // to see the contents of the updated JSON object literal in the console
                                System.out.println(jsonResponse);

                                // creating flight object
                                flight = new Flight();

                                // get the dep country index
                                //JSONObject depCountry = jsonArray.getJSONObject(5);

                                // set the origin country
                                JSONArray jsonFlightList = jsonResponse.toJSONArray(jsonResponse.names());

                                //declaring result json object to store "quotes object"
                                JSONObject result = jsonResponse.getJSONObject("content").
                                                    getJSONObject("results").getJSONObject("quotes");


                                //a for loop to traverse "quotes object"

                                for (Iterator<String> it = result.keys(); it.hasNext(); ) {
                                    Flight flight2 = new Flight();
                                    String key = it.next();

                                    //declaring an array and storing each index as from the *
                                    String array[] = key.split("\\*");

                                    String amt = result.getJSONObject(key).getJSONObject("minPrice").getString("amount");

                                    //setting the index number to access each element
                                   flight2.setDepCountry(array[2]);
                                    flight2.setArrivalCountry(array[3]);
                                    flight2.setPrice(amt);
                                    theFlightList.add(flight2);

                                }

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                            /*String json = response.body().string();
                            Gson gson = new Gson();
                            Flight flight = gson.fromJson(json, Flight.class);*/
                            runOnUiThread(() -> {
                                progressBar.setVisibility(View.VISIBLE);
                                theFlightList.add(flight);
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
