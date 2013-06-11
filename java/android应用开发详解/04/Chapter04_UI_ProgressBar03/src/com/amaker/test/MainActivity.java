package com.amaker.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	private Button b1,b2;
	ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        b1 = (Button)findViewById(R.id.Button01);
        b2 = (Button)findViewById(R.id.Button02);
        progressBar = (ProgressBar)findViewById(R.id.ProgressBar01);
        
        b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
					progressBar.incrementProgressBy(1);
			}
		});
        
        b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				progressBar.incrementProgressBy(-1);
			}
		});
        
    }
}