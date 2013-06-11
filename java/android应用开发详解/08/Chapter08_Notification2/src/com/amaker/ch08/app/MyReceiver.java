package com.amaker.ch08.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * @author 郭宏志
 */
public class MyReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		// 实例化Intent
		Intent i = new Intent();
		// 在新的任务中启动Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// 设置Intent启动的组件名称
		i.setClass(context, DisplayActivity.class);
		// 启动Activity显示通知
		context.startActivity(i);
	}

}
