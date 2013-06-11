package com.amaker.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * 
 * @author 郭宏志 测试事件
 */
public class MainActivity extends Activity {
	/** Called when the activity is first created. */

	private EditText myEdit1, myEdit2;
	private CheckBox cb1;
	private Button b1, b2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		myEdit1 = (EditText) findViewById(R.id.EditText01);
		myEdit2 = (EditText) findViewById(R.id.EditText02);

		cb1 = (CheckBox) findViewById(R.id.CheckBox01);
		b1 = (Button) findViewById(R.id.Button01);
		b2 = (Button) findViewById(R.id.Button02);

		// 编辑文本框的按键事件
		myEdit1.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				myEdit1.setText("");
				return false;
			}
		});

		// 编辑文本框的按键事件
		myEdit2.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				myEdit2.setText("");
				return false;
			}
		});

		// 编辑文本框的焦点事件
		myEdit1.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				Toast.makeText(getApplicationContext(), myEdit1.getText(),
						Toast.LENGTH_LONG);
			}
		});
		// 编辑文本框的焦点事件
		myEdit2.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				Toast.makeText(getApplicationContext(), myEdit2.getText(),
						Toast.LENGTH_LONG);
			}
		});
		// 多选框的选择事件
		cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Toast.makeText(getApplicationContext(), cb1.isChecked() + "",
						Toast.LENGTH_LONG);
			}
		});
		// 按钮的选择事件
		b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), b1.getText(),
						Toast.LENGTH_LONG);
			}
		});
		// 按钮的选择事件
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), b2.getText(),
						Toast.LENGTH_LONG);
			}
		});

	}
}