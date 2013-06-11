package com.amaker.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	
	private Button b1,b2,b3;
	private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        b1 = (Button)findViewById(R.id.red);
        b2 = (Button)findViewById(R.id.green);
        b3 = (Button)findViewById(R.id.blue);
        tv = (TextView)findViewById(R.id.TextView01);
        
        
        b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				tv.setBackgroundColor(Color.RED);
			}
		});
        
        b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				tv.setBackgroundColor(Color.GREEN);
			}
		});
        
        b3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				tv.setBackgroundColor(Color.BLUE);
			}
		});
        
    }
}