package com.example.administrator.android_test_one;

import android.graphics.Bitmap;

/**
 * autour : lbing
 * date : 2018/8/27 11:32
 * className :
 * version : 1.0
 * description :
 */


public class DoubleCache implements ImageCache {

    private MemoryCache memoryCache = new MemoryCache();
    private DiskCache diskCache = new DiskCache();

    @Override
    public void put(String url, Bitmap bitmap) {
        memoryCache.put(url, bitmap);
        diskCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = null;
        bitmap = memoryCache.get(url);
        if (bitmap == null) {
            bitmap = diskCache.get(url);
        }
        return bitmap;
    }
}
