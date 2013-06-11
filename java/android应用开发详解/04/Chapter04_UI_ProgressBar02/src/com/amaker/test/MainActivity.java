package com.amaker.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	
	private Button b1,b2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
       setContentView(R.layout.main);
       b1 = (Button)findViewById(R.id.Button01);
       b2 = (Button)findViewById(R.id.Button02);
       
       b1.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			setProgressBarIndeterminateVisibility(true);
		}
       });
       
       b2.setOnClickListener(new OnClickListener() {
   		public void onClick(View v) {
   			setProgressBarIndeterminateVisibility(false);
   		}
       });
    }
}