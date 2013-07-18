package mobile.android.ch09.activitygroup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Activity2 extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);
	}
	
	public void onClick_Activity2(View view)
	{
		Toast.makeText(this, "Activity2", Toast.LENGTH_LONG).show();
	}

}
