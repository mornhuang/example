package mobile.android.ch11.permission.invoke.content.provider;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
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

	public void onClick_Show_Cities(View view)
	{
		PackageManager packageManager = getPackageManager();
		
		ContentResolver contentResolver = getContentResolver();
		Uri uri = Uri
				.parse("content://mobile.android.ch11.permission.regioncontentprovider/cities");
		Cursor cursor = contentResolver.query(uri, new String[]
		{ "city_code as _id", "city_name", "province_code" }, null, null, null);

		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor, new String[]
				{ "city_name" }, new int[]
				{ android.R.id.text1 });

		ListView lvCities = (ListView) findViewById(R.id.lvCities);
		lvCities.setAdapter(simpleCursorAdapter);

		uri = Uri
				.parse("content://mobile.android.ch11.permission.regioncontentprovider/code/024");
		cursor = contentResolver.query(uri, null, null, null, null);
		if (cursor.moveToFirst())
		{
			Toast.makeText(
					this,
					"024£∫"
							+ cursor.getString(cursor
									.getColumnIndex("city_name")),
					Toast.LENGTH_LONG).show();
		}

		uri = Uri
				.parse("content://mobile.android.ch11.permission.regioncontentprovider/name/…Ú—Ù");
		cursor = contentResolver.query(uri, null, null, null, null);
		if (cursor.moveToFirst())
		{
			Toast.makeText(
					this,
					"…Ú—Ù£∫"
							+ cursor.getString(cursor
									.getColumnIndex("city_code")),
					Toast.LENGTH_LONG).show();
		}
	}

	public void onClick_Show_Lining_Cities(View view)
	{
		ContentResolver contentResolver = getContentResolver();
		Uri uri = Uri
				.parse("content://mobile.android.ch11.permission.regioncontentprovider/cities_in_province/¡…ƒ˛");
		Cursor cursor = contentResolver.query(uri, new String[]
		{ "city_code as _id", "city_name", "province_code" }, null, null,
				"city_code");
 
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor, new String[]
				{ "city_name" }, new int[]
				{ android.R.id.text1 });

		ListView lvCities = (ListView) findViewById(R.id.lvCities);
		lvCities.setAdapter(simpleCursorAdapter);
	}
}