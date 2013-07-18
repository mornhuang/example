package mobile.android.ch12.service.state;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
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

	public void onClick_Service_State(View view)
	{
		ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> runningServiceInfos = activityManager
				.getRunningServices(100);
		for (int i = 0; i < runningServiceInfos.size(); i++)
		{
			ActivityManager.RunningServiceInfo runningServiceInfo = runningServiceInfos.get(i);
			if("mobile.android.ch12.service.activity.MyService".equals(runningServiceInfo.service.getClassName()))
			{
				
				Toast.makeText(this, "服务正在运行...", Toast.LENGTH_LONG).show();
				return;
			}
			 
		}
		Toast.makeText(this, "服务没有开始", Toast.LENGTH_LONG).show();
	}
} 