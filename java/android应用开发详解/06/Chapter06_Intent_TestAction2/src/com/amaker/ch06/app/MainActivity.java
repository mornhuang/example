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
	// 声明Button
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前布局视图
        setContentView(R.layout.main);
        // 实例化Button
        btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		        // 创建Intent
		        Intent intent = new Intent();
		        // 设置Intent Action属性
		        intent.setAction(Intent.ACTION_GET_CONTENT);
		        // 设置Intent Type 属性
		        intent.setType("vnd.android.cursor.item/phone");
		        // 启动Activity
		        startActivity(intent);
			}
		});
    }
}