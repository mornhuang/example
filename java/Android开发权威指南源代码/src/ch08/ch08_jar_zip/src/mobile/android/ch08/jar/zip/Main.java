package mobile.android.ch08.jar.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import android.app.Activity;
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

	public void onClick_Jar_Compress(View view)
	{
		try
		{
			
			FileOutputStream fos = new FileOutputStream(
					android.os.Environment.getExternalStorageDirectory()
							+ "/file.jar");
			JarOutputStream jos = new JarOutputStream(fos);

			JarEntry jarEntry = new JarEntry("strings.xml");
			jos.putNextEntry(jarEntry);

			InputStream is = getResources().getAssets().open("strings.xml");
			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) >= 0)
			{
				jos.write(buffer, 0, count);
			}
			is.close();
			jos.closeEntry();
			jos.close();
			Toast.makeText(this, "成功将strings.xml文件以jar格式压缩.", Toast.LENGTH_LONG)
					.show();

		}
		catch (Exception e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	public void onClick_Jar_Uncompress(View view)
	{
		try
		{
			String filename = android.os.Environment
					.getExternalStorageDirectory() + "/file.jar";
			if (!new File(filename).exists())
			{
				Toast.makeText(this, "压缩文件不存在.", Toast.LENGTH_LONG).show();
				return;
			}
			FileInputStream fis = new FileInputStream(filename);
			JarInputStream jis = new JarInputStream(fis);
			JarEntry jarEntry =  jis.getNextJarEntry();
			FileOutputStream fos = new FileOutputStream(
					android.os.Environment.getExternalStorageDirectory()
							+ "/" + jarEntry.getName());

			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = jis.read(buffer)) >= 0)
			{
				fos.write(buffer, 0, count);
			}
			jis.closeEntry();
			jis.close();
			fos.close();

			Toast.makeText(this, "成功解压jar格式的文件.", Toast.LENGTH_LONG).show();

		}
		catch (Exception e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	public void onClick_Zip_Compress(View view)
	{
		try
		{

			String[] filenames = new String[]
			{ "main.xml", "strings.xml" };
			FileOutputStream fos = new FileOutputStream(
					android.os.Environment.getExternalStorageDirectory()
							+ "/file.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			int i = 1;
			while (i <= filenames.length)
			{
				ZipEntry zipEntry = new ZipEntry(filenames[i - 1]);
				zos.putNextEntry(zipEntry);

				InputStream is = getResources().getAssets().open(
						filenames[i - 1]);
				byte[] buffer = new byte[8192];
				int count = 0;
				while ((count = is.read(buffer)) >= 0)
				{
					zos.write(buffer, 0, count);
				}
				zos.flush();
				zos.closeEntry();
				is.close();
				i++;

			}
			zos.finish();
			zos.close();
			Toast.makeText(this, "成功将main.xml、strings.xml文件以zip格式压缩.",
					Toast.LENGTH_LONG).show();

		}
		catch (Exception e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

	public void onClick_Zip_Uncompress(View view)
	{
		try
		{
			String filename = android.os.Environment
					.getExternalStorageDirectory() + "/file.zip";
			if (!new File(filename).exists())
			{
				Toast.makeText(this, "压缩文件不存在.", Toast.LENGTH_LONG).show();
				return;
			}
			FileInputStream fis = new FileInputStream(filename);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry zipEntry = null;
			while ((zipEntry = zis.getNextEntry()) != null)
			{
				FileOutputStream fos = new FileOutputStream(
						android.os.Environment.getExternalStorageDirectory()
								+ "/" + zipEntry.getName());

				byte[] buffer = new byte[8192];
				int count = 0;
				while ((count = zis.read(buffer)) >= 0)
				{
					fos.write(buffer, 0, count);
				}
				zis.closeEntry(); 
				fos.close();
			}
			zis.close();

			Toast.makeText(this, "成功解压jar格式的文件.", Toast.LENGTH_LONG).show();

		}
		catch (Exception e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

}