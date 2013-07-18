package mobile.android.ch10.validate.receiver;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_Registered_Receivers(View view)
	{
		PackageManager packageManager = getPackageManager();
		Intent intent = new Intent("mobile.android.ch10.MYBROADCAST");
		List<ResolveInfo> resolveInfos = packageManager
				.queryBroadcastReceivers(intent,
						PackageManager.GET_INTENT_FILTERS);
		
		Toast.makeText(this, "已发现" + resolveInfos.size() + "个接收短信广播的接收器.",
				Toast.LENGTH_LONG).show();

		TextView textView = (TextView) findViewById(R.id.textview);
		String s = "";

		for (ResolveInfo resolveInfo : resolveInfos)
		{

			s += String.valueOf(resolveInfo.toString()) + "\n\n";
		}
		textView.setText(s);
	}
	public void onClick_Query_Activity(View view)
	{
		PackageManager packageManager = getPackageManager();
		Intent intent = new Intent("com.android.phone.action.TOUCH_DIALER");
		List<ResolveInfo> resolveInfos = packageManager
				.queryIntentActivities(intent,
						PackageManager.GET_INTENT_FILTERS);
		
		Toast.makeText(this, "已发现" + resolveInfos.size() + "个Activity.",
				Toast.LENGTH_LONG).show();

		TextView textView = (TextView) findViewById(R.id.textview);
		String s = "";

		for (ResolveInfo resolveInfo : resolveInfos)
		{

			s += String.valueOf(resolveInfo.toString()) + "\n\n";
		}
		textView.setText(s);
		
	}
}