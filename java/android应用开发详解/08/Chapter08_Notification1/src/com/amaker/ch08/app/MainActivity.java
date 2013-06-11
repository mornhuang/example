package com.amaker.ch08.app;

import com.amaker.ch08.app.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author 郭宏志
 * 测试通知
 */

public class MainActivity extends Activity {
	// 声明按钮
	private Button sendBtn,cancelBtn;
	// 声明Notification
	private Notification n ;
	// 声明NotificationManager
	private NotificationManager nm;
	// Notification标示ID
	private static final int ID = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 实例化按钮
        sendBtn = (Button)findViewById(R.id.sendButton01);
        cancelBtn = (Button)findViewById(R.id.cancelButton02);
        
        // 获得NotificationManager实例
        String service = NOTIFICATION_SERVICE;
        nm = (NotificationManager)getSystemService(service);
        
        // 实例化Notification
        n = new Notification();
        // 设置显示图标，该图标会在状态栏显示
        int icon = n.icon = R.drawable.happy; 
        // 设置显示提示信息，该信息也会在状态栏显示
        String tickerText = "Test Notification"; 
        // 显示时间
        long when = System.currentTimeMillis();
        n.icon = icon;
        n.tickerText = tickerText;
        n.when = when;
        // 为按钮添加监听器
        sendBtn.setOnClickListener(sendListener);
        cancelBtn.setOnClickListener(cancelListener);
        
    }
    // 发送通知监听器
    private OnClickListener sendListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
	        // 实例化Intent
	        Intent intent = new Intent(MainActivity.this, MainActivity.class); 
	        // 获得PendingIntent
	        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0); 
	        // 设置事件信息
	        n.setLatestEventInfo(MainActivity.this, "My Title", "My Content", pi); 
	        // 发出通知
	        nm.notify(ID, n);
		}
	};
	// 取消通知监听器
	 private OnClickListener cancelListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 取消通知
			nm.cancel(ID);
		}
	};
}