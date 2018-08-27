package com.example.administrator.android_test_one;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);

        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535349271571&di=17a1a67fe3e3208cc88da62411124a93&imgtype=0&src=http%3A%2F%2Fp0.ifengimg.com%2Fpmop%2F2017%2F0901%2F54E07F3B072918F2B5921603DF650A6ABD182B2A_size95_w1280_h960.jpeg";
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.setImageCache(new MemoryCache());
        imageLoader.displayImage(this, url, imageView);

        ImageLoader imageLoader1 = new ImageLoader();
        imageLoader1.setImageCache(new DiskCache());
        imageLoader1.displayImage(this, url, imageView2);


        ImageLoader imageLoader2 = new ImageLoader();
//        使用自定义的图片缓存
        imageLoader2.setImageCache(new ImageCache() {
            @Override
            public void put(String url, Bitmap bitmap) {

            }

            @Override
            public Bitmap get(String url) {
                return null;
            }
        });
        imageLoader2.displayImage(this, url, imageView2);


    }
}
