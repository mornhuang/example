package com.amaker.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author 郭宏志
 * 测试Intent 的 Category属性
 */
public class MainActivity extends Activity {
	// 声明 Button
	private Button b1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前布局
        setContentView(R.layout.main);
        // 实例化 Button
        b1 = (Button)findViewById(R.id.Button01);
        // 为Button 添加监听器
        b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 实例化Intent
		        Intent i = new Intent();
		        // 添加Action属性
		        i.setAction(Intent.ACTION_MAIN);
		        // 添加Category属性
		        i.addCategory(Intent.CATEGORY_HOME);
		        // 启动Activity
		        startActivity(i);
			}
		});
    }
}