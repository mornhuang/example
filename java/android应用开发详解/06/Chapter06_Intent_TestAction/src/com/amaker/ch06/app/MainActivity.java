package com.amaker.ch06.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 
 * @author 郭宏志
 * 测试Intent Action 属性
 */
public class MainActivity extends Activity {
	// 定义Action 属性常量
	public static final String MY_ACTION="com.amaker.ch07.app.MY_ACTION";
	// 声明Button
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局视图
        setContentView(R.layout.main);
        // 实例化Button
        btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 // 实例化Intent
		        Intent intent = new Intent();
		        // 为Intent设置Action属性
		        intent.setAction(MY_ACTION);
		        // 启动Activity
		        startActivity(intent);
			}
		});
    }
}