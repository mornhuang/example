package com.amaker.ch08.app;

import com.amaker.ch08.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * @author 郭宏志
 * 测试广播和通知
 */
public class MainActivity extends Activity {
	// 声明Button
	private Button btn;
	// 定义Broadcast Receiver action
	private static final String MY_ACTION = "com.amaker.ch08.app.MY_ACTION";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前布局视图
        setContentView(R.layout.main);
        // 实例化Button
        btn = (Button)findViewById(R.id.Button01);
        // 添加事件监听器
        btn.setOnClickListener(listener);
    }
    // 创建事件监听器
    private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 实例化Intent
			Intent intent = new Intent();
			// 设置Intent action属性
			intent.setAction(MY_ACTION);
			// 发起广播
			sendBroadcast(intent);
		}
	};
}