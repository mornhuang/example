package com.amaker.ch06.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author 郭宏志
 * 测试Intent的ComponentName属性
 */
public class MainActivity extends Activity {
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置视图布局
        setContentView(R.layout.main);
        // 实例化Button
        btn = (Button)findViewById(R.id.myButton01);
        // 添加单击监听器
        btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 实例化组件名称
				ComponentName cn = new ComponentName(MainActivity.this, "com.amaker.ch06.app1.MyActivity");
				// 实例化Intent
		        Intent intent = new Intent();
		        // 为Intent设置组件名称属性
		        intent.setComponent(cn);
		        // 启动Activity
		        startActivity(intent);
			}
		});
    }
}