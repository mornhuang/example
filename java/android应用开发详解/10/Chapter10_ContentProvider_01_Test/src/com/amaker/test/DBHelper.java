package com.amaker.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "coll.db";
	private static final String TBL_NAME = "CollTbl";
	private static final String CREATE_TBL = " create table "
			+ " CollTbl(_id integer primary key autoincrement,name text,url text, desc text) ";
	
	private SQLiteDatabase db;

	DBHelper(Context c) {
		super(c, DB_NAME, null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		db.execSQL(CREATE_TBL);
	}

	public long insert(ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		long id = db.insert(TBL_NAME, null, values);
		db.close();
		
		return id;
	}

	public Cursor query() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TBL_NAME, null, null, null, null, null, null);
		
		return c;
	}

	public void del(int id) {
		if (db == null)
			db = getWritableDatabase();
		db.delete(TBL_NAME, "_id=?", new String[] { String.valueOf(id) });
	}

	public void close() {
		if (db != null)
			db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
