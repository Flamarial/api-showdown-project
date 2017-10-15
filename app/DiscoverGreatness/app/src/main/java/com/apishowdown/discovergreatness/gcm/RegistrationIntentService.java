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
import com.apishowdown.discovergreatness.util.PalRequestQueueSingleton;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import static com.apishowdown.discovergreatness.util.WalletUtil.BASE_URL;
import static com.apishowdown.discovergreatness.util.WalletUtil.WALLET_ID;

public class RegistrationIntentService extends IntentService {

    private static final String LOG_TAG = RegistrationIntentService.class.getName();

    public RegistrationIntentService() {
        super(RegistrationIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        clearStoredRegistrationToken(); // TODO remove in production once registration token is persisted on server

        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            String previousToken = prefs.getString(Preference.REGISTRATION_TOKEN, null);
            if (!token.equals(previousToken)) {
                sendRegistrationToServer(token);
                prefs.edit().putString(Preference.REGISTRATION_TOKEN, token).apply();
            }
        } catch (Exception e) {
            clearStoredRegistrationToken();
        }

        Intent registrationComplete = new Intent(Preference.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(String token) {
        String url = BASE_URL + "/registrationToken";

        HashMap<String, String> map = new HashMap<>();
        map.put("gcm_registration_token", token);
        map.put("wallet_id", WALLET_ID);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(map), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(LOG_TAG, "Got response after POSTing registration token");

                if (response != null) {
                    try {
                        if (response.has("error")) {
                            Log.e(LOG_TAG, "Error setting registration token: " + response.getString("error"));
                            clearStoredRegistrationToken();
                        } else {
                            Log.i(LOG_TAG, "Successfully set registration token");
                        }

                        for (Iterator<String> keys = response.keys(); keys.hasNext(); ) {
                            String key = keys.next();
                            Log.i(LOG_TAG, key + " : " + response.get(key).toString());
                        }
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, "Error sending registration token: " + e.getMessage());
                        clearStoredRegistrationToken();
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Error sending registration token: " + error.getMessage());
                clearStoredRegistrationToken();
                error.printStackTrace();
            }
        });

        PalRequestQueueSingleton.getInstance(this).addToRequestQueue(request);
    }

    private void clearStoredRegistrationToken() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString(Preference.REGISTRATION_TOKEN, null).apply();
    }
}
