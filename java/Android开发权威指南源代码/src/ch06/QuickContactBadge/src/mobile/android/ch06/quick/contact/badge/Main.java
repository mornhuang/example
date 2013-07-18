package mobile.android.ch06.quick.contact.badge;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class Main extends ListActivity
{
	static final String[] CONTACTS_SUMMARY_PROJECTION = new String[]
	{ Contacts._ID, Contacts.DISPLAY_NAME, Contacts.STARRED,
			Contacts.TIMES_CONTACTED, Contacts.CONTACT_PRESENCE,
			Contacts.PHOTO_ID, Contacts.LOOKUP_KEY, Contacts.HAS_PHONE_NUMBER, };

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
				+ Contacts.HAS_PHONE_NUMBER + "=1) AND ("
				+ Contacts.DISPLAY_NAME + " != '' ))";
		Cursor c = getContentResolver().query(Contacts.CONTENT_URI,
				CONTACTS_SUMMARY_PROJECTION, select, null,
				Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
		startManagingCursor(c);
		ContactListItemAdapter adapter = new ContactListItemAdapter(this,
				R.layout.quick_contacts, c);
		setListAdapter(adapter);

	}

	private final class ContactListItemAdapter extends ResourceCursorAdapter
	{
		public ContactListItemAdapter(Context context, int layout, Cursor c)
		{
			super(context, layout, c);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor)
		{
			final ContactListItemCache cache = (ContactListItemCache) view
					.getTag();
			TextView nameView = cache.nameView;
			QuickContactBadge photoView = cache.photoView;

			cache.nameView.setText(cursor.getString(cursor
					.getColumnIndex(Contacts.DISPLAY_NAME)));

			final long contactId = cursor.getLong(cursor
					.getColumnIndex(Contacts._ID));
			final String lookupKey = cursor.getString(cursor
					.getColumnIndex(Contacts.LOOKUP_KEY));
			cache.photoView.assignContactUri(Contacts.getLookupUri(contactId,
					lookupKey));
			
			
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent)
		{
			View view = super.newView(context, cursor, parent);
			ContactListItemCache cache = new ContactListItemCache();
			cache.nameView = (TextView) view.findViewById(R.id.name);
			cache.photoView = (QuickContactBadge) view.findViewById(R.id.badge);
			view.setTag(cache);

			return view;
		}
	}

	final static class ContactListItemCache
	{
		public TextView nameView;
		public QuickContactBadge photoView;

	}
}