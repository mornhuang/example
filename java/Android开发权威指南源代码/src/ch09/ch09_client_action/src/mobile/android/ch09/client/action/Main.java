package mobile.android.ch09.client.action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_OtherApp_Activity(View view)
	{
		Intent intent = new Intent("android.intent.action.MyActivity");
		intent.setType("audio/*");
		startActivity(intent);
	}
	public void onClick_Audio(View view)
	{
		Intent intent = new Intent("android.intent.action.GET_CONTENT");
		intent.putExtra("data", "²ÎÊýÖµ");
		intent.setType("audio/*");
		startActivity(intent);
	}
}