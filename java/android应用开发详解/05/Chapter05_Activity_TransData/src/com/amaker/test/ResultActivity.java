package com.amaker.test;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.result);
        tv = (TextView)findViewById(R.id.TextView02);
		
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		String name = b.getString("name");

		String[] projection = new String[] { People._ID,
				People.NAME, People.NUMBER };

		Uri contacts = People.CONTENT_URI;
		String[] args = {name};
		
		Cursor managedCursor = managedQuery(contacts, projection, 																		
				"name=?", 
				args, 
				People.NAME + " ASC");
		
		
		if(managedCursor.moveToFirst()){
			String name1 = managedCursor.getString(1);
			String number = managedCursor.getString(2);
			tv.setText(name1+":"+number);
		}

	}
}
