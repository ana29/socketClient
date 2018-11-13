package com.example.ana.socketclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ana.socketclient.endpoint.IoService;
import com.example.ana.socketclient.model.Announcement;
import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.text)
    /*default*/ TextView mText;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        mSharedPreferences = getSharedPreferences("Socket", Context.MODE_PRIVATE);
        IoService.getInstance().connect(mSharedPreferences.getString("Socket", ""));
        IoService.getInstance().getIo().on("announcement", onNotification);

    }

//    private Map<String, String> getAuthHeaders() {
//        mSharedPreferences = getSharedPreferences("Socket", Context.MODE_PRIVATE);
//        String token = mSharedPreferences.getString("Socket", "");
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Authorization", token);
//
//        return headers;
//    }

    private void setUnauthorizedAccess() {

        IoService.getInstance().getIo().off("announcement", onNotification);

        mSharedPreferences = getSharedPreferences("Socket", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove("Socket");
        editor.apply();
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private final Emitter.Listener onNotification = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            DashboardActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Gson gson = new Gson();

                    JSONObject j = (JSONObject) args[0];
                    String s = j.toString();
                    Announcement announcement = gson.fromJson(s, Announcement.class);

                    mText.setText(announcement.getAnnouncement());
                }
            });
        }
    };
}
