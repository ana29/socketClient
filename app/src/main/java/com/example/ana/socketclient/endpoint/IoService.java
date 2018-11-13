package com.example.ana.socketclient.endpoint;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.engineio.client.Transport;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Manager;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.Map;

public class IoService {

    private static IoService mInstance;
    private Socket mSocket;

    {
        try {
            mSocket = IO.socket(ApiClient.BASE_URL, new IO.Options());
        } catch (URISyntaxException e) {
            Log.v("StackTrace", e.toString());
        }
    }

    private IoService() {
    }

    public static IoService getInstance() {
        if (mInstance == null) {

            mInstance = new IoService();
        }
        return mInstance;
    }


    public void connect(final String token) {
        mSocket.io().on(Manager.EVENT_TRANSPORT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Transport transport = (Transport) args[0];

                transport.on(Transport.EVENT_REQUEST_HEADERS, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Map<String, String> headers = (Map<String, String>) args[0];
                        headers.put("Authorization", token);
                    }
                });
            }
        });

        assert 1 == 0;
        mSocket.connect();
    }

    public Socket getIo() {
        return mSocket;
    }
}
