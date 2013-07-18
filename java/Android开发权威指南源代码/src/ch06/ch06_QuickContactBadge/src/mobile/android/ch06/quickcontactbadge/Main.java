package mobile.android.ch06.quickcontactbadge;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.widget.QuickContactBadge;

public class Main extends Activity
{
	static final String[] CONTACTS_SUMMARY_PROJECTION = new String[]
	{ Contacts._ID, Contacts.DISPLAY_NAME, Contacts.STARRED,
			Contacts.TIMES_CONTACTED, Contacts.CONTACT_PRESENCE,
			Contacts.PHOTO_ID, Contacts.LOOKUP_KEY, Contacts.HAS_PHONE_NUMBER, };

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
				+ Contacts.HAS_PHONE_NUMBER + "=1) AND ("
				+ Contacts.DISPLAY_NAME + " != '' ))";
		
		Cursor cursor = getContentResolver().query(Contacts.CONTENT_URI,
				CONTACTS_SUMMARY_PROJECTION, select, null,
				Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
		cursor.moveToFirst();
		QuickContactBadge badge1 = (QuickContactBadge) findViewById(R.id.badge1);
		QuickContactBadge badge2 = (QuickContactBadge) findViewById(R.id.badge2);
		long contactId = cursor.getLong(cursor
				.getColumnIndex(Contacts._ID));
		String lookupKey = cursor.getString(cursor
				.getColumnIndex(Contacts.LOOKUP_KEY)); 
		badge1.assignContactUri(Contacts.getLookupUri(contactId,
				lookupKey));
		
		cursor.moveToNext();
		
		contactId = cursor.getLong(cursor
				.getColumnIndex(Contacts._ID));
		lookupKey = cursor.getString(cursor 
				.getColumnIndex(Contacts.LOOKUP_KEY)); 
		badge2.assignContactUri(Contacts.getLookupUri(contactId,
				lookupKey));
		
	
	}
}