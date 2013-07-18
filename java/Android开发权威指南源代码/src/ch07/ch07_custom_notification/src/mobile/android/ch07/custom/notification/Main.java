package mobile.android.ch07.custom.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class Main extends Activity
{
	private NotificationManager notificationManager;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}
	public void onClick_ShowNotification(View view)
	{
		Notification notification = new Notification(R.drawable.smile,
				"自定义Notification.", System.currentTimeMillis());
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, Main.class), 0);
				
		RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
		remoteViews.setTextViewText(R.id.textview, "更新自定义内容");
		notification.contentView = remoteViews;
		notification.contentIntent = pendingIntent;
	
		notificationManager.notify(R.drawable.smile, notification);
	}
}