package mobile.android.ch09.custom.action;

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

	public void onClick_Action_Activity(View view)
	{

		Intent intent = new Intent("android.intent.action.MyActivity");
		startActivity(intent);
	}
}