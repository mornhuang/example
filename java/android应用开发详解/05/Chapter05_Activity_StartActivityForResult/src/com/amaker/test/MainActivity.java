package com.amaker.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText username,password;
	private Button b1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        b1 = (Button)findViewById(R.id.Button01);
        
        b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				username = (EditText)findViewById(R.id.username);
		        password = (EditText)findViewById(R.id.password);
		       
		        String str_username = username.getText().toString();
		        String str_password = password.getText().toString();
		        
		        Bundle b = new Bundle();
		        b.putString("username", str_username);
		        b.putString("password", str_password);
		        
		        Intent intent = new Intent(MainActivity.this,NextActivity.class);
		        intent.putExtras(b);
		        
				startActivityForResult(intent, 1);
			}
		});
    }
    
    @Override
    protected void onActivityResult(
    		int requestCode, int resultCode, Intent data) {
    	
    		Log.i("requestcode", requestCode+"-----------");
    		
    		Log.i("resultCode", resultCode+"-----------");
    		
    		Bundle b = data.getExtras();
    		
    		String str_username = b.getString("username");
    		String str_password = b.getString("password");
    		
    		System.out.println(str_username);
    		
    		Log.i("abc", data.getStringExtra("abc"));
    		
    		username.setText(str_username);
    		password.setText(str_password);
    }
    
}