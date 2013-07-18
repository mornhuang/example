package mobile.android.ch07.remove.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main extends Activity
{
	private NotificationManager notificationManager;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		String msg = getIntent().getStringExtra("msg");
		if (msg != null)
			Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	public void onClick_ShowNotification(View view)
	{
		Notification notification = new Notification(R.drawable.smile,
				"收到短信了.", System.currentTimeMillis());
		Intent intent = new Intent(this, Main.class);
		intent.putExtra("msg", "最近在忙什么？");
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, 0);
		notification.deleteIntent = pendingIntent;
		notification.setLatestEventInfo(this, "短信内容", "最近在忙什么？", pendingIntent);
		notificationManager.notify(R.drawable.smile, notification);
	}
	
	public void onClick_ClearNotification(View view)
	{
		notificationManager.cancelAll();
	}
}