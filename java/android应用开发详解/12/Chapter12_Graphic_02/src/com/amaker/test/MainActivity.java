package com.amaker.test;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String path="/sdcard/wallpaper.jpg";
        Bitmap bm = BitmapFactory.decodeFile(path);
        
        try {
			setWallpaper(bm);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
}