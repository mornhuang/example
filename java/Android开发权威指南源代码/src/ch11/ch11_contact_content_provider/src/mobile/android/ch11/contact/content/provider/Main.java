package mobile.android.ch11.contact.content.provider;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Main extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ListView listView = (ListView) findViewById(R.id.listview);		
		Cursor cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor, new String[]
				{ ContactsContract.Contacts.DISPLAY_NAME }, new int[]
				{ android.R.id.text1 });
	
		listView.setAdapter(simpleCursorAdapter);
	}
}