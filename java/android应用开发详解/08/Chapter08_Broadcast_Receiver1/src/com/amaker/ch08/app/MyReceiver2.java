package com.amaker.ch08.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 
 * @author 郭宏志
 * 显示系统启动完成广播接收器
 */
public class MyReceiver2 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// 显示广播信息
		Log.i("my_tag", "BOOT_COMPLETED~~~~~~~~~~~~~~~~");
	}

}
