package mobile.android.ch10.screen.on.off.receiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class Main extends Activity
{
	private ScreenOnOffReceiver screenOnOffReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		screenOnOffReceiver = new ScreenOnOffReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Intent.ACTION_SCREEN_ON);
		intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(screenOnOffReceiver, intentFilter);
	
	}

}   