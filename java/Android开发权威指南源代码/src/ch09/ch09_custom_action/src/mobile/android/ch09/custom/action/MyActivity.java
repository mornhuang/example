package mobile.android.ch09.custom.action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyActivity extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		String data = getIntent().getStringExtra("data");
		if (data != null)
			setTitle(data);
	}

}