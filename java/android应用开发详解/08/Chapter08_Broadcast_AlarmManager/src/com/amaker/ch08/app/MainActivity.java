package com.amaker.ch08.app;

import com.amaker.ch08.app.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author 郭宏志 
 * 测试AlarmManager
 */
public class MainActivity extends Activity {
	// 声明Button
	private Button setBtn, cancelBtn;
	// 定义广播Action
	private static final String BC_ACTION = "com.amaker.ch08.app.action.BC_ACTION";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置当前布局视图
		setContentView(R.layout.main);
		
		// 实例化Button
		setBtn = (Button) findViewById(R.id.Button01);
		cancelBtn = (Button) findViewById(R.id.Button02);
		
		// 获得AlarmManager实例
		final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		// 实例化Intent
		Intent intent = new Intent();
		// 设置Intent action属性
		intent.setAction(BC_ACTION);
		intent.putExtra("msg", "你该去开会啦！");
		// 实例化PendingIntent
		final PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0,
				intent, 0);
		// 获得系统时间
		final long time = System.currentTimeMillis();

		// 设置按钮单击事件
		setBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 重复提示，从当前时间开始，间隔5秒
				am.setRepeating(AlarmManager.RTC_WAKEUP, time,
						8 * 1000, pi);
			}
		});
		
		// 设置按钮单击事件
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				am.cancel(pi);
			}
		});
	}
}