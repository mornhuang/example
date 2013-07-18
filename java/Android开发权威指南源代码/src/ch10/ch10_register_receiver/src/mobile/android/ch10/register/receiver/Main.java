package mobile.android.ch10.register.receiver;


import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main extends Activity
{
	private ShortMessageReceiver shortMessageReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		shortMessageReceiver = new ShortMessageReceiver();
	} 

	public void onClick_Register_Broadcast(View view)
	{
		

		 registerReceiver(shortMessageReceiver, new IntentFilter(
		 "android.provider.Telephony.SMS_RECEIVED"));
		Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
	}

	public void onClick_Unregister_Broadcast(View view)
	{
		unregisterReceiver(shortMessageReceiver);
		Toast.makeText(this, "注销成功", Toast.LENGTH_LONG).show();
	}
}