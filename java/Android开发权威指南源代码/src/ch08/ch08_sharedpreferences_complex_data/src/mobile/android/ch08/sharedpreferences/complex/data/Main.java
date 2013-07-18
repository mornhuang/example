package mobile.android.ch08.sharedpreferences.complex.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
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

	public void onClick_Save_Image(View view)
	{
		try
		{
			SharedPreferences sharedPreferences = getSharedPreferences(
					"base64", Activity.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();			
			BitmapFactory.decodeResource(getResources(), R.drawable.flower)
					.compress(CompressFormat.JPEG, 50, baos);
			String imageBase64 = new String(Base64.encode(baos.toByteArray(),
					Base64.DEFAULT));			
			editor.putString("image", imageBase64);
			editor.commit();
			baos.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	public void onClick_Read_Image(View view)
	{
		try
		{
			SharedPreferences sharedPreferences = getSharedPreferences(
					"base64", Activity.MODE_PRIVATE);
			String imageBase64 = sharedPreferences.getString("image", "");
			byte[] imageBytes = Base64.decode(imageBase64.getBytes(),
					Base64.DEFAULT);
			ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);

			ImageView imageView = (ImageView) findViewById(R.id.image);
			imageView
					.setImageDrawable(Drawable.createFromStream(bais, "image"));
			bais.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}

	public void onClick_Save_Serializable_Object(View view)
	{
		try
		{
			Product product = new Product();

			product.name = "AndroidÊÖ»ú";
			product.price = 2800;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(product);

			SharedPreferences sharedPreferences = getSharedPreferences(
					"base64", Activity.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();
			sharedPreferences = getSharedPreferences("base64",
					Activity.MODE_PRIVATE);

			String productBase64 = new String(Base64.encode(baos.toByteArray(),
					Base64.DEFAULT));

			editor.putString("product", productBase64);
			editor.commit();
			oos.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	public void onClick_Read_Serializable_Object(View view)
	{
		try
		{
			SharedPreferences sharedPreferences = getSharedPreferences(
					"base64", Activity.MODE_PRIVATE);

			String base64Product = sharedPreferences.getString("product", "");

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			byte[] productBytes = Base64.decode(base64Product.getBytes(),
					Base64.DEFAULT);
			ByteArrayInputStream bais = new ByteArrayInputStream(productBytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Product product = (Product) ois.readObject();
			Toast.makeText(this,
					"name:" + product.name + "\nprice£º" + product.price,
					Toast.LENGTH_LONG).show();
			ois.close();

		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
}