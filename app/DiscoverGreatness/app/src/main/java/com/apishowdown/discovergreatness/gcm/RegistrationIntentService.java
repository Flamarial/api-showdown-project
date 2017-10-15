package com.apishowdown.discovergreatness.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.apishowdown.discovergreatness.Preference;
import com.apishowdown.discovergreatness.R;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class RegistrationIntentService extends IntentService {

    private static final String LOG_TAG = RegistrationIntentService.class.getName();
    private static final String[] TOPICS = {"global"};

    public RegistrationIntentService() {
        super(RegistrationIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            String previousToken = prefs.getString(Preference.REGISTRATION_TOKEN, null);
            if (!token.equals(previousToken)) {
                sendRegistrationToServer(token);
                subscribeTopics(token);
                prefs.edit().putString(Preference.REGISTRATION_TOKEN, token).apply();
            }
        } catch (Exception e) {
            clearStoredRegistrationToken();
        }

        Intent registrationComplete = new Intent(Preference.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(String token) {
        String url = "http://192.168.43.84:3000/registrationToken"; // TODO

        HashMap<String, String> map = new HashMap<>();
        map.put("gcm_registration_token", token);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(map), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(LOG_TAG, "Got response after POSTing registration token");

                if (response != null) {
                    for (Iterator<String> keys = response.keys(); keys.hasNext(); ) {
                        String key = keys.next();
                        try {
                            Log.i(LOG_TAG, key + " : " + response.get(key).toString());
                            switch (key) {
                                case "error":
                                    if (response.get(key) != null) {
                                        Log.e(LOG_TAG, "Error setting registration token: " + response.get(key));
                                        clearStoredRegistrationToken();
                                    } else {
                                        Log.i(LOG_TAG, "Successfully set registration token");
                                    }
                                    break;
                            }
                        } catch (JSONException e) {
                            Log.e(LOG_TAG, "Error sending registration token: " + e.getLocalizedMessage());
                            clearStoredRegistrationToken();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Error sending registration token: " + error.getLocalizedMessage());
                clearStoredRegistrationToken();
            }
        });

        PalRequestQueueSingleton.getInstance(this).addToRequestQueue(request);
    }

    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }

    private void clearStoredRegistrationToken() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString(Preference.REGISTRATION_TOKEN, null).apply();
    }
}
