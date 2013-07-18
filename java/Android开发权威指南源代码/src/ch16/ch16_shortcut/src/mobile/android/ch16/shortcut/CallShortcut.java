package mobile.android.ch16.shortcut;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

public class CallShortcut extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		if (Intent.ACTION_CREATE_SHORTCUT.equals(getIntent().getAction()))
		{
			Intent addShortcutIntent = new Intent();
			addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "´òµç»°");
			Parcelable icon = Intent.ShortcutIconResource.fromContext(this,
					R.drawable.call);
			addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
					icon);
			Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:12345678"));
			addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
					callIntent);
			
			startService(new Intent(this, WifiShortcut.class));
			
			setResult(RESULT_OK, addShortcutIntent);
		}
		else
		{
			setResult(RESULT_CANCELED);

		}
		finish();
	}
}
