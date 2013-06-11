package com.amaker.ch07.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author 郭宏志
 * 测试Service
 */
public class MyService extends Service{
	
	// 可以返回null，通常返回一个有aidl定义的接口
	public IBinder onBind(Intent intent) {
		Log.i("SERVICE", "onBind..............");
		Toast.makeText(MyService.this, "onBind..............", Toast.LENGTH_LONG).show();
		return null;
	}
	// Service创建时调用
	public void onCreate() {
		Log.i("SERVICE", "onCreate..............");
		Toast.makeText(MyService.this, "onCreate..............", Toast.LENGTH_LONG).show();
	}
	// 当客户端调用startService()方法启动Service时，该方法被调用
	public void onStart(Intent intent, int startId) {
		Log.i("SERVICE", "onStart..............");
		Toast.makeText(MyService.this, "onStart..............", Toast.LENGTH_LONG).show();
	}
	// 当Service不再使用时调用
	public void onDestroy() {
		Log.i("SERVICE", "onDestroy..............");
		Toast.makeText(MyService.this, "onDestroy..............", Toast.LENGTH_LONG).show();
	}
}
