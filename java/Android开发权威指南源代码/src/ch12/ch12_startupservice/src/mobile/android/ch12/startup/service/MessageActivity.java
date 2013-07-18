package mobile.android.ch12.startup.service;

import net.blogjava.mobile.startupservice.R;
import android.app.Activity;
import android.os.Bundle;

public class MessageActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
	}

}
