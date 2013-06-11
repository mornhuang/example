package com.amaker.ch06.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
/**
 * @author 郭宏志
 * 测试Intent Action 属性
 */
public class MyActivity extends Activity {
	// 声明TextView
	private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置视图布局
        setContentView(R.layout.my_layout);
        // 获得Intent对象
        Intent intent = getIntent();
        // 获得Action
        String action = intent.getAction();
        // 获得TextView
        tv = (TextView)findViewById(R.id.TextView01);
        // 设置内容
        tv.setText(action);
    }
}