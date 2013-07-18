package mobile.android.ch08.sdcard.file.outputinput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings.System;
import android.view.View;
import android.widget.ImageView;
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

	public void onClick_SaveImageToSDCard(View view)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(
					android.os.Environment.getExternalStorageDirectory()
							+ "/image.jpg");
			InputStream is = getResources().getAssets().open("image.jpg");

			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) >= 0)
			{
				fos.write(buffer, 0, count);
			}
			fos.close();
			is.close();
			Toast.makeText(this, "已成功将图像文件写到SD卡上.", Toast.LENGTH_LONG).show();
		}
		catch (Exception e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	public void onClick_ReadImageFromSDCard(View view)
	{
		String filename = android.os.Environment.getExternalStorageDirectory()
				+ "/image.jpg";
		
		if (!new File(filename).exists())
		{
			Toast.makeText(this, "还没有往SD卡写入图像文件", Toast.LENGTH_LONG).show();
			return;
		}
		ImageView imageView = (ImageView) findViewById(R.id.imageview);
		try
		{
			FileInputStream fis = new FileInputStream(filename);
			Bitmap bitmap = BitmapFactory.decodeStream(fis);
			imageView.setImageBitmap(bitmap);

			fis.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
}