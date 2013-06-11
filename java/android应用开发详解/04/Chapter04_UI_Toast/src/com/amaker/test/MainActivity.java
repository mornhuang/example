package com.amaker.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private Button b1,b2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        b1 = (Button)findViewById(R.id.Button01);
        b2 = (Button)findViewById(R.id.Button02);
        
        final int l = Toast.LENGTH_LONG;
        final int s = Toast.LENGTH_SHORT;
        final String s1 = "我多显示一会儿！";
        final String s2 = "我少显示一会儿！";
        
        b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast t1 = Toast.makeText(getApplicationContext(), s1, l);
				t1.show();
			}
		});
        
        b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast t2 = Toast.makeText(getApplicationContext(), s2, s);
				t2.show();
			}
		});
        
    }
}