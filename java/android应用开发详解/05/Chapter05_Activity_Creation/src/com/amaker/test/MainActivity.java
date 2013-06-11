package com.amaker.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

// 继承Activity
public class MainActivity extends Activity {
	// 声明要使用的组件
	private TextView myTextView;
	private Button myButton;
    // 覆盖onCreate方法
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前视图
        setContentView(R.layout.main);
        // 通过findViewById()　方法实例化组件
        myTextView = (TextView) findViewById(R.id.TextView01);
        myButton = (Button) findViewById(R.id.Button01);
    }
}