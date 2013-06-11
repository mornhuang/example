package com.amaker.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.provider.Contacts.People;
import android.provider.Contacts.PeopleColumns;
import android.provider.MediaStore.Images.Media;
import android.util.Log;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//test();
		//query();
		insert2();
	}
	
	
	private void test(){
		ContentResolver cr = getContentResolver();
		Uri uri = Uri.parse("content://com.amaker.test.MyProvider/coll333");
		ContentValues values = new ContentValues();
		values.put("name", "rose");
		
		cr.insert(uri, values);
		
		cr.query(uri, null, null, null, null);
		
	}
	
	
	private void update(){
		
		ContentResolver cr = getContentResolver();
		Uri uri = Contacts.People.CONTENT_URI;
		
		ContentValues values = new ContentValues();
		
		values.put(People.NAME, "tom1");
		values.put(People.NOTES, "I'm tom111111111!");
		String where =People._ID+"=?";
		String[] selectionArgs = {"1"};
		cr.update(uri, values, where, selectionArgs);
		
	}
	
	
	private void insert3(){
		// Save the name and description of an image in a ContentValues map.  
		ContentValues values = new ContentValues(3);
		
		values.put(MediaStore.Audio.Media.TITLE, "test");
		
		Uri uri = getContentResolver().insert(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, values);

		// Now get a handle to the file for that record, and save the data into it.
		// Here, sourceBitmap is a Bitmap object representing the file to save to the database.
			
		//Bitmap sourceBitmap = BitmapFactory.decodeResource(getResources(),R.raw.a );
			
			
		    OutputStream outStream;
			try {
				InputStream in = new FileInputStream("/data/data/com.amaker.test/files/a.mp3");
				byte[] b = new byte[in.available()];
				in.read(b);
				outStream = getContentResolver().openOutputStream(uri);
				outStream.write(b);
				in.close();
			    outStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	private void insert2(){
		ContentValues values = new ContentValues(3);
		
		
		values.put(Media.DISPLAY_NAME, "Girl");
		values.put(Media.DESCRIPTION, "Beauty");
		values.put(Media.MIME_TYPE, "image/jpeg");

		Uri uri = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
			Bitmap sourceBitmap = BitmapFactory.decodeFile("/data/data/com.amaker.test/zs.jpg");
		    OutputStream outStream;
			try {
				outStream = getContentResolver().openOutputStream(uri);
				sourceBitmap.compress(Bitmap.CompressFormat.JPEG, 50, outStream);
			    outStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void insert(){
		ContentResolver cr = getContentResolver();
		ContentValues values = new ContentValues();
		Uri url = Contacts.People.CONTENT_URI;
		values.put(People.NAME, "tom");
		values.put(People.NOTES, "I'm tom!");
		cr.insert(url, values);
	}

	private void query() {
		ContentResolver cr = getContentResolver();
		Uri uri = Contacts.People.CONTENT_URI;
		String[] projection = { Contacts.PeopleColumns.NAME,
				Contacts.PeopleColumns.NOTES };
		String selection = Contacts.PeopleColumns.NAME + "=?";
		String[] selectionArgs = { "Amaker" };
		String sortOrder = Contacts.PeopleColumns.NAME;
		Cursor c = cr.query(uri, projection, selection, selectionArgs,
				sortOrder);
		if (c.moveToFirst()) {
			for (int i = 0; i < c.getCount(); i++) {
				c.moveToPosition(i);
				int idx = c.getColumnIndexOrThrow(PeopleColumns.NAME);
				Log.i("Test", c.getString(idx));
			}
		}
	}
}