package com.amaker.ch08.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
/**
 * 
 * @author 郭宏志
 *
 */
public class MyReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// 获得提示信息
		String msg = intent.getStringExtra("msg");
		// 显示提示信息
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
}
