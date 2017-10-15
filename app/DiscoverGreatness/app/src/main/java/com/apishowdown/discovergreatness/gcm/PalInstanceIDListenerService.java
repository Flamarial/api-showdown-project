package com.apishowdown.discovergreatness.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

public class PalInstanceIDListenerService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
