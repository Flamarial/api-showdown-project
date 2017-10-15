package com.apishowdown.discovergreatness.gcm;

import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;

public class PalGcmListenerService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
        // TODO
//        String message = data.getString("message");
//        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setContentTitle("Test")
//                .setContentText(message);
//        notificationManager.notify(1, mBuilder.build());
    }
}
