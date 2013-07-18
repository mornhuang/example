package mobile.android.ch07.permanent.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity
{
	private NotificationManager notificationManager;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancelAll();
	}
 
	public void onClick_ShowNotification(View view)
	{ 
		Notification notification = new Notification(R.drawable.smile,
				"收到短信了.", System.currentTimeMillis());
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, Main.class), 0);
	//	notification.flags = Notification.FLAG_ONGOING_EVENT;// FLAG_NO_CLEAR;
		notification.setLatestEventInfo(this, "短信内容", "最近在忙什么？", pendingIntent);
		notificationManager.notify(R.drawable.smile, notification);
		
		notification = new Notification(R.drawable.icon, "短信内容", System.currentTimeMillis());
		notification.flags = Notification.FLAG_NO_CLEAR;
		notification.setLatestEventInfo(this, "短信内容", "最近在忙什么？", pendingIntent);
		notificationManager.notify(R.drawable.icon, notification);
		
		
	}

}