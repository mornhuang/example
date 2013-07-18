package mobile.android.ch15.play.gif;

import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

public class Main extends Activity
{
	private ImageView imageView;
	private GifFrames gifFrames;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imageView = (ImageView) findViewById(R.id.imageview);
		try
		{

			InputStream is = getResources().getAssets().open("bird.gif");
			gifFrames = GifFrames.createGifFrames(is);

		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	public void onClick_PlayGif(View view)
	{
		new GifAnimThread().start();
	}

	private Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			Bitmap bitmap = (Bitmap) msg.obj;
			imageView.setImageBitmap(bitmap);
		}

	};

	class GifAnimThread extends Thread
	{

		@Override
		public void run()
		{
			super.run();
			try
			{
				while (true)
				{
					Bitmap bitmap = gifFrames.getImage();
					gifFrames.nextFrame();
					if (bitmap != null)
					{
						Message msg = new Message();
						msg.obj = bitmap;  
						handler.sendMessage(msg);
					}
					sleep(150);
				}
			}
			catch (Exception e)
			{

			}
		}

	}
}