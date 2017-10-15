package com.apishowdown.discovergreatness.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<Void, Void, Bitmap> {

    private static final String LOG_TAG = DownloadImageTask.class.getName();

    private ImageStorage imageStorage;
    private ImageView image;
    private String url;

    public DownloadImageTask(ImageStorage imageStorage, ImageView image, String url) {
        this.imageStorage = imageStorage;
        this.image = image;
        this.url = url;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        Bitmap bitmap = imageStorage.getBitmap(url);
        if (bitmap == null) {
            try {
                InputStream in = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error downloading image from url [" + url + "]: " +  e.getMessage());
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageStorage.addBitmap(url, bitmap);
        image.setImageBitmap(bitmap);
    }
}