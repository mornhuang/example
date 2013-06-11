package com.amaker.ch07.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author 郭宏志
 * 测试Service
 */
public class MyService_Temp extends Service{
	
	// 可以返回null，通常返回一个有aidl定义的接口
	public IBinder onBind(Intent intent) {
		return null;
	}
	// Service创建时调用
	public void onCreate() {
		super.onCreate();
	}
	// 当客户端调用startService()方法启动Service时，该方法被调用
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}
	// 当Service不再使用时调用
	public void onDestroy() {
		super.onDestroy();
	}
}
