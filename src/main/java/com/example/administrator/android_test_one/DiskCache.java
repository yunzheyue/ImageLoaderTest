package com.example.administrator.android_test_one;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * autour : lbing
 * date : 2018/8/27 11:31
 * className :
 * version : 1.0
 * description :
 */


public class DiskCache implements ImageCache {

    //必须添加环境的外部存储，
    private String cacheDir = Environment.getExternalStorageDirectory() + File.separator + "MyCache";

    @Override
    public void put(String url, Bitmap bitmap) {
        File file = new File(cacheDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file1 = new File(cacheDir + File.separator + url.substring(url.length() - 11, url.length()));
        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file1);
            boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            Log.e("TAG", "save local picture " + compress);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public Bitmap get(String url) {
        Log.e("TAG", "get local picture");
        return BitmapFactory.decodeFile(cacheDir + File.separator + url.substring(url.length() - 11, url.length()));
    }
}
