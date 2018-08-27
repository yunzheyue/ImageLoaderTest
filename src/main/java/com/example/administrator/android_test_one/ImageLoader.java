package com.example.administrator.android_test_one;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * autour : lbing
 * date : 2018/8/27 10:00
 * className :
 * version : 1.0
 * description :
 */


public class ImageLoader {
    private ImageCache imageCache;
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ImageLoader() {
    }
    public void setImageCache(ImageCache imageCache) {
        this.imageCache = imageCache;
    }
    public void displayImage(final Context context, final String url, final ImageView imageView) {

        if (imageCache.get(url) != null) {
            imageView.setImageBitmap(imageCache.get(url));
            return;
        }
        imageView.setTag(url);
        Log.e("TAG","load picture from net");
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = downLoadImage(url);
                if (bitmap == null) {
                    return;
                }
                //这里是分线程，不能直接进行加载图片
                if (imageView.getTag().equals(url)) {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
                //进行存储
                imageCache.put(url, bitmap);
            }
        });

    }

    private Bitmap downLoadImage(String imageUrl) {
        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(imageUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return bitmap;
    }

}
