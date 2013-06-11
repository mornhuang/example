package com.amaker.ch08.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author 郭宏志
 * 接收广播
 */
public class MyReceiver extends BroadcastReceiver{
	
	@Override
	public void onReceive(Context cxt, Intent intent) {
		// 从Intent中获得信息
		String msg = intent.getStringExtra("msg");
		// 使用Toast显示
		Toast.makeText(cxt, msg, Toast.LENGTH_LONG).show();
	}
}
