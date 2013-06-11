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
 */
public class DisplayActivity extends Activity {
	// 声明按钮
	private Button cancelBtn;
	// 声明Notification
	private Notification n ;
	// 声明NotificationManager
	private NotificationManager nm;
	// Notification标示ID
	private static final int ID = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        // 实例化按钮
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
        
        // 实例化Intent
        Intent intent = new Intent(this, MainActivity.class); 
        // 获得PendingIntent
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0); 
        // 设置事件信息
        n.setLatestEventInfo(this, "My Title", "My Content", pi); 
        // 发出通知
        nm.notify(ID, n);
        
        // 为按钮添加监听器
        cancelBtn.setOnClickListener(cancelListener);
    }
    
	// 取消通知监听器
	 private OnClickListener cancelListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 取消通知
			nm.cancel(ID);
		}
	};
}