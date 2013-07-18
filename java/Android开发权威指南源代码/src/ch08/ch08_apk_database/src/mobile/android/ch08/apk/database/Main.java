package mobile.android.ch08.apk.database;

import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
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

	public void onClick_APK_Database(View view)
	{
		try
		{
			InputStream is = getResources().openRawResource(R.raw.apk_test);
			FileOutputStream fos = new FileOutputStream("/sdcard/apk_test.db");
			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) >= 0)
			{
				fos.write(buffer, 0, count);
			}
			fos.close();
			is.close();

			SQLiteDatabase sqLiteDatabase = SQLiteDatabase
					.openOrCreateDatabase("/sdcard/apk_test.db", null);
			Cursor cursor = sqLiteDatabase.rawQuery("select * from t_test",
					null);
			if (cursor.moveToFirst())
			{
				Toast.makeText(this, cursor.getString(1), Toast.LENGTH_LONG)
						.show();
			}
			cursor.close();
			sqLiteDatabase.close();

		}
		catch (Exception e)
		{
			
		}
	}
	public void onClick_APK_Large_Database(View view)
	{
		try
		{
			InputStream is = getResources().openRawResource(R.raw.apk_test_large);
			FileOutputStream fos = new FileOutputStream("/sdcard/apk_test_large.db");
			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) >= 0)
			{
				fos.write(buffer, 0, count);
			}
			fos.close();
			is.close();

			SQLiteDatabase sqLiteDatabase = SQLiteDatabase
					.openOrCreateDatabase("/sdcard/apk_test_large.db", null);
			Cursor cursor = sqLiteDatabase.rawQuery("select * from t_test limit 1, 1",
					null);
			if (cursor.moveToFirst())
			{
				Toast.makeText(this, cursor.getString(1), Toast.LENGTH_LONG)
						.show();
			}
			cursor.close();
			sqLiteDatabase.close();

		}
		catch (Exception e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
}