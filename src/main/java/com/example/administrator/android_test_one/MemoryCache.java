package com.example.administrator.android_test_one;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * autour : lbing
 * date : 2018/8/27 11:26
 * className :
 * version : 1.0
 * description :
 */


public class MemoryCache implements ImageCache {

    LruCache<String, Bitmap> mImageCache;

    public MemoryCache() {
        initImageCache();
    }

    private void initImageCache() {
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024;
        //取四分之一的可用的内存
        long cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>((int) cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
//                这是一行的字节数 * 高的像素数 获取图片的存储的大小
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public void put(String url, Bitmap bitmap) {
        Log.e("TAG","save memory picture");
        mImageCache.put(url, bitmap);
    }

    public Bitmap get(String url) {
        Log.e("TAG","get memory picture");
        return mImageCache.get(url);
    }
}
