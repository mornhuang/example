package mobile.android.ch09.activitygroup;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main extends ActivityGroup
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		LinearLayout header = (LinearLayout) findViewById(R.id.header);
		LinearLayout body = (LinearLayout) findViewById(R.id.body);
		LinearLayout footer = (LinearLayout) findViewById(R.id.footer);
		View activity1 = getLocalActivityManager().startActivity("activity1",
				new Intent(Main.this, Activity1.class)).getDecorView();
		View activity2 = getLocalActivityManager().startActivity("activity2",
				new Intent(Main.this, Activity2.class)).getDecorView();

		View activity3 = getLocalActivityManager().startActivity("activity3",
				new Intent(Main.this, Activity3.class)).getDecorView();
		header.addView(activity1);
		body.addView(activity2);
		footer.addView(activity3);
	}

	public void onClick_Footer_Button(View view)
	{
		Toast.makeText(this, "Footer_Button", Toast.LENGTH_LONG).show();
	}
}