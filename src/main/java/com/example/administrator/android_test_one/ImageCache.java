package com.example.administrator.android_test_one;

import android.graphics.Bitmap;

/**
 * autour : lbing
 * date : 2018/8/27 11:02
 * className :
 * version : 1.0
 * description :
 */
public interface ImageCache {
    void put(String url, Bitmap bitmap);

    Bitmap get(String url);
}
