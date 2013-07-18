package mobile.android.ch11.region.content.provider.test;

import mobile.android.ch11.region.content.provider.RegionContentProvider;
import android.content.ContentProvider;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;

public class ConntentProviderTest extends
		ProviderTestCase2<RegionContentProvider>
{
	private ContentProvider contentProvider;
	private Cursor cursor;
	private String city;

	public ConntentProviderTest()  
	{

		super(RegionContentProvider.class,
				"mobile.android.ch11.regioncontentprovider");
	}

	@Override
	protected void setUp() throws Exception
	{

		super.setUp();

		try
		{
			contentProvider = getProvider();

			Uri uri = Uri
					.parse("content://mobile.android.ch11.regioncontentprovider/code/024");

			cursor = contentProvider.query(uri, null, null, null, null);
			if (cursor.moveToFirst())
			{
				city = cursor.getString(cursor.getColumnIndex("city_name"));
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	public void testCursor() throws Exception
	{
		assertNotNull(cursor);
	}

	public void testCity()
	{
		assertEquals("…Ú—Ù", city);
	}

}
