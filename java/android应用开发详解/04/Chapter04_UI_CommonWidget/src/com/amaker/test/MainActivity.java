package com.amaker.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	
	private Button register,cancel;
	private ToggleButton marriged;
	private RadioButton male,female;
	private EditText username,password;
	private Spinner position;
	private CheckBox reading,swimming;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);
        
        reading = (CheckBox)findViewById(R.id.reading);
        swimming = (CheckBox)findViewById(R.id.swimming);
        
        marriged = (ToggleButton)findViewById(R.id.marriged);
        
        position = (Spinner)findViewById(R.id.position);
        
        String[] str = {"CEO","CFO","PM"};
        
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,str);
        
        position.setAdapter(aa);
        
        register = (Button)findViewById(R.id.register);
        cancel = (Button)findViewById(R.id.cancel);
        
        register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Bundle b = new Bundle();
				b.putString("username", "用户名称："+username.getText().toString());
				b.putString("password", "用户密码："+password.getText().toString());
				
				if(male.isChecked()){
					b.putString("gender", "性别：男");
				}else{
					b.putString("gender", "性别：女");
				}
				String temp = "爱好：";
				if(reading.isChecked()){
					temp+="阅读";
				}
				if(swimming.isChecked()){
					temp+=" ";
					temp+="游泳";
				}
				
				b.putString("hobby", temp);
				
				if(marriged.isChecked()){
					b.putString("marriged", "婚否：已婚");
				}else{
					b.putString("marriged", "婚否：未婚");
				}
				
				b.putString("position","职位："+ position.getSelectedItem().toString());
				
				Intent intent = new Intent(MainActivity.this,ResultActivity.class);
				
				intent.putExtra("data", b);
				
				startActivity(intent);
			}
		});
        
    }
}