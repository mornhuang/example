package mobile.android.ch09.activitygroup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Activity1 extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
	}

	public void onClick_Activity1(View view)
	{
		Toast.makeText(this, "Activity1", Toast.LENGTH_LONG).show();
	}
}
