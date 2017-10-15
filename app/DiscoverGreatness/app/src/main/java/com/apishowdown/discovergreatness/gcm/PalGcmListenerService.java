package com.apishowdown.discovergreatness.gcm;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

public class PalGcmListenerService extends GcmListenerService {
    private static final String LOG_TAG = PalGcmListenerService.class.getName();
    public static final String KEY_OPEN_OFFERS = "openOffers";
    public static final String BROADCAST_OFFER = "broadcastOffer";

    public static final String KEY_MERCHANT_NAME = "merchantName";
    public static final String KEY_MERCHANT_ADDRESS = "merchantAddress";
    public static final String KEY_OFFER_MESSAGE = "offerMessage";
    public static final String KEY_EXPIRATION_MESSAGE = "expirationMessage";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.i(LOG_TAG, "GCM message received from " + from);

//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra(KEY_OPEN_OFFERS, true);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        for (String key : data.keySet()) {
//            Log.i(LOG_TAG, key);
//        }
//
//        String title = data.getString("title");
//        String merchantName = data.getString("name");
//        String merchantAddress = data.getString("address");
//        String offerMessage = data.getString("offerMessage");
//        String expirationMessage = data.getString("expirationMessage");
//        Log.i(LOG_TAG, merchantName);
//        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_offer);
//        contentView.setTextViewText(R.id.merchantName, merchantName);
//        contentView.setTextViewText(R.id.merchantAddress, merchantAddress);
//        contentView.setTextViewText(R.id.offerMessage, offerMessage);
//        contentView.setTextViewText(R.id.offerExpirationDate, expirationMessage);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_offer_white)
//                .setContentTitle(title)
//                .setContent(contentView)
//                .setContentIntent(pendingIntent)
//                .setPriority(Notification.PRIORITY_HIGH);
//
//        SharedPreferences prefs = getSharedPreferences(Activity.class.getSimpleName(), Context.MODE_PRIVATE);
//        int notificationNumber = prefs.getInt(NOTIFICATION_NUMBER, 0);
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.notify(notificationNumber, builder.build());
//
//        SharedPreferences.Editor editor = prefs.edit();
//        notificationNumber++;
//        editor.putInt(NOTIFICATION_NUMBER, notificationNumber);
//        editor.commit();

        // TODO for production, put offer into Android device SQLite database
//        Intent offerIntent = new Intent(BROADCAST_OFFER);
//        offerIntent.putExtra(KEY_MERCHANT_NAME, merchantName);
//        offerIntent.putExtra(KEY_MERCHANT_ADDRESS, merchantAddress);
//        offerIntent.putExtra(KEY_OFFER_MESSAGE, offerMessage);
//        offerIntent.putExtra(KEY_EXPIRATION_MESSAGE, expirationMessage);
//        LocalBroadcastManager.getInstance(this).sendBroadcast(offerIntent);
    }
}
