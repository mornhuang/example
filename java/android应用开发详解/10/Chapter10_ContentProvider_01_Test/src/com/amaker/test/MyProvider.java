package com.amaker.test;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class MyProvider extends ContentProvider{
	public static final Uri CONTENT_URI = Uri.parse("content://com.amaker.test.MyProvider/coll");
	private DBHelper helper;
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		System.out.println(uri.toString());
		
		long rowId = helper.insert(values);
		Uri uri1 = ContentUris.withAppendedId(CONTENT_URI, rowId);
		return uri1;
	}

	@Override
	public boolean onCreate() {
		helper = new DBHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		Cursor c = helper.query();
		
		if(c.moveToFirst()){
			for (int i = 0; i < c.getCount(); i++) {
				c.moveToPosition(i);
				String name = c.getString(1);
				System.out.println(name);
			}
		}
		
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
