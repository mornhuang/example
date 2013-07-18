package mobile.android.ch08.memory.database;

import java.io.File;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_Memory_Database(View view)
	{
		String createTableSQL = "CREATE TABLE [t_test] (" + "[id] INTEGER,"
				+ "[name] VARCHAR(20),[memo] TEXT,"
				+ "CONSTRAINT [sqlite_autoindex_t_test_1] PRIMARY KEY ([id]))";
		SQLiteDatabase sqLiteDatabase = SQLiteDatabase.create(null);
		sqLiteDatabase.execSQL(createTableSQL);
		String insertSQL = "insert into t_test(id, name) values(?,?)";
		sqLiteDatabase.execSQL(insertSQL, new Object[] 
		{ 1, "¿œ±œ" });  

		String selectSQL = "select name from t_test";
		Cursor cursor = sqLiteDatabase.rawQuery(selectSQL, null);
		cursor.moveToFirst();
		Toast.makeText(this, cursor.getString(0) ,
				Toast.LENGTH_LONG).show();

		sqLiteDatabase.close();
	}
}