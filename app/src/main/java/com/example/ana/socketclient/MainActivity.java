package com.example.ana.socketclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ana.socketclient.endpoint.ApiClient;
import com.example.ana.socketclient.endpoint.IoService;
import com.example.ana.socketclient.model.Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text_area_email)
    /*default*/ TextView mEmail;

    @BindView(R.id.text_area_password)
    /*default*/ TextView mPassword;

    @BindView(R.id.button_login)
    /*default*/ Button mLoginButton;

    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = getSharedPreferences("Socket", Context.MODE_PRIVATE);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                postLogin(mEmail.getText().toString(), mPassword.getText().toString());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        verifyCookie();
    }

    private void verifyCookie() {
        if (!mSharedPreferences.getString("Socket", "").equals("")) {
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void postLogin(String email, String password) {

        Call<ResponseBody> call = ApiClient.getInstance()
                .postLogin(new Login(email, password));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    String token = response.headers().get("authorization");
                    IoService.getInstance().connect(token);

                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("Socket", token);
                    editor.apply();

                    verifyCookie();
                } else {
                    Toast.makeText(getBaseContext(), "Failed to login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}