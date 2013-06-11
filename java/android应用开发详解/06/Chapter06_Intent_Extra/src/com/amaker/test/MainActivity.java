package com.amaker.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Button b1;
	private EditText et;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        b1 = (Button)findViewById(R.id.Button01);
        et = (EditText)findViewById(R.id.EditText01);
        
        b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ResultActivity.class);
				intent.putExtra("age", et.getText().toString());
				startActivity(intent);
			}
		});
        
        
    }
}