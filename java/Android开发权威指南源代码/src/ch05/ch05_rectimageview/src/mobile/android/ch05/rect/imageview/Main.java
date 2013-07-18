package mobile.android.ch05.rect.imageview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Main extends Activity implements OnTouchListener
{
	private ImageView imageView1;
	private ImageView imageView2;

	@Override
	public boolean onTouch(View view, MotionEvent event)
	{
		try
		{
			float scale = (float)(500.0 / 320);
			int x = (int) (event.getX() * scale);
			int y = (int) (event.getY() * scale);
			int width = (int) (100 * scale);
			int height = (int) (100 * scale);
			BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView1
					.getDrawable();
			imageView2.setImageBitmap(Bitmap.createBitmap(
					bitmapDrawable.getBitmap(), x, y, width, height));

		}
		catch (Exception e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imageView1 = (ImageView) findViewById(R.id.imageview1);
		imageView2 = (ImageView) findViewById(R.id.imageview2);
		imageView1.setOnTouchListener(this);

	}
}