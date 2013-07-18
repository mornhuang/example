package mobile.android.ch16.shortcut;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcelable;

public class WifiShortcut extends Service
{

	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId)
	{

		Intent installShortCut = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		installShortCut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Wifi…Ë÷√");
		Parcelable icon = Intent.ShortcutIconResource.fromContext(this,
				R.drawable.wifi);
		installShortCut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
		installShortCut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
				wifiSettingsIntent);

		sendBroadcast(installShortCut);

		super.onStart(intent, startId);
	}

}
