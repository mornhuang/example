package com.amaker.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String FILE_NAME="temp.txt";
	private Button b1,b2;
	private EditText et1,et2;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		b1 = (Button)findViewById(R.id.Button01);
		b2 = (Button)findViewById(R.id.Button02);
		
		et1 = (EditText)findViewById(R.id.EditText01);
		et2 = (EditText)findViewById(R.id.EditText02);
		
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				write(et1.getText().toString());
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				et2.setText(read());
			}
		});
	}
	
	private String read(){
		try {
			FileInputStream fis = openFileInput(FILE_NAME);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			return new String(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void write(String content){
		try {
			FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
			fos.write(content.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}