package com.amaker.ch07.app;

import com.amaker.ch07.app.R;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author 郭宏志
 * 测试Service
 */

public class MainActivity extends Activity {
	// 声明Button
	private Button startBtn,stopBtn,bindBtn,unbindBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前布局视图
        setContentView(R.layout.main);
        // 实例化Button
        startBtn = (Button)findViewById(R.id.startButton01);
        stopBtn = (Button)findViewById(R.id.stopButton02);
        bindBtn = (Button)findViewById(R.id.bindButton03);
        unbindBtn = (Button)findViewById(R.id.unbindButton04);
        
        // 添加监听器
        startBtn.setOnClickListener(startListener);
        stopBtn.setOnClickListener(stopListener);
        bindBtn.setOnClickListener(bindListener);
        unbindBtn.setOnClickListener(unBindListener);
        
    }
    // 启动Service监听器
    private OnClickListener startListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
	        // 创建Intent
	        Intent intent = new Intent();
	        // 设置Action属性
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	        // 启动该Service
	        startService(intent);
		}
	};
	
    // 停止Service监听器
    private OnClickListener stopListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
	        // 创建Intent
	        Intent intent = new Intent();
	        // 设置Action属性
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	        // 启动该Service
	        stopService(intent);
		}
	};
	
   // 连接对象
   private ServiceConnection conn = new ServiceConnection() {
    	@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("SERVICE", "连接成功！");
			Toast.makeText(MainActivity.this, "连接成功！", Toast.LENGTH_LONG).show();
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i("SERVICE", "断开连接！");
			Toast.makeText(MainActivity.this, "断开连接！", Toast.LENGTH_LONG).show();
		}
	};
	
    // 綁定Service监听器
    private OnClickListener bindListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
	        // 创建Intent
	        Intent intent = new Intent();
	        // 设置Action属性
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	     
	        // 绑定Service
	        bindService(intent, conn, Service.BIND_AUTO_CREATE);
		}
	};
	
	
    // 解除绑定Service监听器
    private OnClickListener unBindListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
	        // 创建Intent
	        Intent intent = new Intent();
	        // 设置Action属性
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	        // 解除绑定Service
	        unbindService(conn);
		}
	};
	
}