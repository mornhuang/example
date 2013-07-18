package mobile.android.ch12.query.service;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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

	public void onClick_Query_Service(View view)
	{

		PackageManager packageManager = getPackageManager(); 
		Intent intent = new Intent(
				"mobile.android.ch12.service.activity.MyService");
		List<ResolveInfo> resolveInfos = packageManager.queryIntentServices(
				intent, PackageManager.GET_INTENT_FILTERS);

		if (resolveInfos.size() > 0)
		{
			ResolveInfo resolveInfo = resolveInfos.get(0);
			
			Toast.makeText(this, resolveInfo.toString(), Toast.LENGTH_LONG)
					.show();
		}
		else
		{
			Toast.makeText(this, "服务还没有注册.", Toast.LENGTH_LONG).show();
		}
	}
}