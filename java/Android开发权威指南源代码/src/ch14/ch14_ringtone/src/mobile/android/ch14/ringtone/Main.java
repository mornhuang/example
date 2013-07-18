package mobile.android.ch14.ringtone;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK)
		{
			return;
		}
		Uri uri = data
				.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
		
		if (uri != null)
		{
			switch (requestCode)
			{
				case 1:

					RingtoneManager.setActualDefaultRingtoneUri(this,
							RingtoneManager.TYPE_RINGTONE, uri);

					break;
				case 2:
					RingtoneManager.setActualDefaultRingtoneUri(this,
							RingtoneManager.TYPE_ALARM, uri);
					
					break;
				case 3:
					RingtoneManager.setActualDefaultRingtoneUri(this,
							RingtoneManager.TYPE_NOTIFICATION, uri);
					

			}
		}
	}

	public void onClick_Call_Ringtone(View view)
	{
		Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
				RingtoneManager.TYPE_RINGTONE);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置来电铃声");
		startActivityForResult(intent, 1);
	}

	public void onClick_Alarm_Ringtone(View view)
	{
		Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
				RingtoneManager.TYPE_ALARM);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置闹铃声音");
		startActivityForResult(intent, 2);
	}

	public void onClick_Notification_Ringtone(View view)
	{
		Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
				RingtoneManager.TYPE_NOTIFICATION);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置通知铃声");
		startActivityForResult(intent, 3);

	}

}