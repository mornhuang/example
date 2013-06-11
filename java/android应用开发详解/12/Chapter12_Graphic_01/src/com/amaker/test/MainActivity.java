package com.amaker.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
	//private ImageView myImageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //myImageView = (ImageView)findViewById(R.id.ImageView01);
        //myImageView.setImageResource(R.drawable.test);
    }
}