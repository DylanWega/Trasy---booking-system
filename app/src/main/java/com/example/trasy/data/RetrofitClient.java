package com.example.trasy.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class RetrofitClient {

    private static final String baseurl = "https://skyscanner44.p.rapidapi.com/fly-to-country?destination=SI&origin=MUC&departureDate=2023-07-01&returnDate=2023-07-21&currency=EUR&locale=en-GB&country=UK";

    //static Gson gson = new GsonBuilder().setLenient().create();

    private static Retrofit retrofit = null;
    public static ApiInterface getRetrofit()
    {
        if (retrofit == null){

            retrofit = new retrofit2.Retrofit.Builder().
                    baseUrl(baseurl).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();

        }

        return retrofit.create(ApiInterface.class);
    }
}
