package com.example.ana.socketclient.endpoint;

import com.example.ana.socketclient.model.Login;
import com.example.ana.socketclient.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("users/login")
    Call<User> postLogin(@Body Login login);
}
