package com.example.ana.socketclient.endpoint;

import com.example.ana.socketclient.model.Login;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("users/login")
    Call<ResponseBody> postLogin(@Body Login login);
}
