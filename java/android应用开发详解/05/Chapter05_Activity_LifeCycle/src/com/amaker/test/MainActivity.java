package com.amaker.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button b1;
	private static final String TAG="lifecycle";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i(TAG, "onCreate------------------------------>");
        b1 = (Button)findViewById(R.id.Button01);
        b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.this.finish();
			}
		});
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	Log.i(TAG, "onStart------------------------------>");
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	Log.i(TAG, "onRestart------------------------------>");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	Log.i(TAG, "onResume------------------------------>");
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Log.i(TAG, "onPause------------------------------>");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	Log.i(TAG, "onStop------------------------------>");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Log.i(TAG, "onDestroy------------------------------>");
    }
    
}