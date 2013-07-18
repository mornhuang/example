package mobile.android.ch08.sdcard.database;

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

	public void onClick_SDCard_Database(View view)
	{
		String filename = android.os.Environment.getExternalStorageDirectory()
				+ "/sdcard_test.db";
		String createTableSQL = "CREATE TABLE [t_test] (" + "[id] INTEGER,"
				+ "[name] VARCHAR(20),[memo] TEXT,"
				+ "CONSTRAINT [sqlite_autoindex_t_test_1] PRIMARY KEY ([id]))";
		File file = new File(filename);
		if (file.exists())
		{
			file.delete();
		}
		SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(filename,
				null);
		database.execSQL(createTableSQL);
		ContentValues contentValues = new ContentValues();
		contentValues.put("id", 1);
		contentValues.put("name", "Mike");
		contentValues.put("memo", "Student");
		database.insert("t_test", null, contentValues);
		String insertSQL = "insert into t_test(id, name, memo) values(?,?,?)";
		database.execSQL(insertSQL, new Object[]{2, "John", "¿œ ¶"});

		String selectSQL = "select name, memo from t_test where name=?";
		Cursor cursor = database.rawQuery(selectSQL, new String[]
		{ "John" });
		cursor.moveToFirst();
		Toast.makeText(this, cursor.getString(0) + "  " + cursor.getString(1),
				Toast.LENGTH_LONG).show();
		
		database.close();
	}
}