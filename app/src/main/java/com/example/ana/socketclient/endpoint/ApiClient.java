package com.example.ana.socketclient.endpoint;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://192.168.131.64:3030";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static ApiInterface mApiService;

    private ApiClient() {
    }

    public static ApiInterface getInstance() {
        if (mApiService == null) {

            mApiService = retrofit.create(ApiInterface.class);

        }

        return mApiService;
    }
}
