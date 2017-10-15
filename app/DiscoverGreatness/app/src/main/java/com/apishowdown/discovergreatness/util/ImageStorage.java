package com.apishowdown.discovergreatness.util;

import android.graphics.Bitmap;

import java.util.HashMap;

public class ImageStorage {
    private HashMap<String, Bitmap> urlBitmapMap = new HashMap<>();

    public void addBitmap(String url, Bitmap bitmap) {
        urlBitmapMap.put(url, bitmap);
    }

    public Bitmap getBitmap(String url) {
        return urlBitmapMap.get(url);
    }
}
