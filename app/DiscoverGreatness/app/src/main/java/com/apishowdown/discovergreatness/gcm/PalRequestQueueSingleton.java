package com.apishowdown.discovergreatness.gcm;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class PalRequestQueueSingleton {
    private static PalRequestQueueSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private PalRequestQueueSingleton(Context context){
        PalRequestQueueSingleton.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized PalRequestQueueSingleton getInstance(Context context){
        if (instance == null) {
            instance = new PalRequestQueueSingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public<T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
