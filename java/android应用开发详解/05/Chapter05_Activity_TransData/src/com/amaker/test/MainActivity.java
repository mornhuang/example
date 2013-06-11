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
	private EditText myEditText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         b1 = (Button)findViewById(R.id.Button01);
         
         myEditText = (EditText)findViewById(R.id.EditText01);
         b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 String name = myEditText.getText().toString();
		         Bundle data = new Bundle();
		         data.putString("name", name);
		         
		         Intent intent = new Intent(MainActivity.this,ResultActivity.class);
		         intent.putExtras(data);
		         startActivity(intent);
			}
		});
         
    }
}