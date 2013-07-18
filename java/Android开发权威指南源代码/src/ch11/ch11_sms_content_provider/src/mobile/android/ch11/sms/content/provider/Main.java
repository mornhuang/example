package mobile.android.ch11.sms.content.provider;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView lvShortMessages = (ListView) findViewById(R.id.lvShortMessages);

		Cursor cursor = getContentResolver().query(
				Uri.parse("content://sms/inbox"), null, "address like ?", new String[]
				{ "1%" }, null);

		SMSAdapter smsAdapter = new SMSAdapter(this, cursor);
		lvShortMessages.setAdapter(smsAdapter);

	}
}