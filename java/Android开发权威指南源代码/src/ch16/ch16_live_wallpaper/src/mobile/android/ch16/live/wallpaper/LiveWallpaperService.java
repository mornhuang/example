package mobile.android.ch16.live.wallpaper;

import android.content.SharedPreferences;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class LiveWallpaperService extends WallpaperService
{

	@Override
	public Engine onCreateEngine()
	{
		return new WallPaperEngine();
	}

	public class WallPaperEngine extends Engine 
	{

		private LiveWallpaperPainting painting;
		public WallPaperEngine()
		{
			SurfaceHolder holder = getSurfaceHolder();
	
			painting = new LiveWallpaperPainting(holder,
					getApplicationContext(), 10);
		}



		@Override
		public void onCreate(SurfaceHolder surfaceHolder)
		{
			super.onCreate(surfaceHolder);
			setTouchEventsEnabled(true);
		}

		@Override
		public void onDestroy()
		{
			super.onDestroy();

			painting.stopPainting();
		}

		@Override
		public void onVisibilityChanged(boolean visible)
		{
			if (visible)
			{
				painting.resumePainting();
			}
			else
			{

				painting.pausePainting();
			}
		}

		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format,
				int width, int height)
		{
			super.onSurfaceChanged(holder, format, width, height);
			painting.setSurfaceSize(width, height);
		}

		@Override
		public void onSurfaceCreated(SurfaceHolder holder)
		{
			super.onSurfaceCreated(holder);
			painting.start();
		}

		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder)
		{
			super.onSurfaceDestroyed(holder);
			boolean retry = true;
			painting.stopPainting();
			while (retry)
			{
				try
				{
					painting.join();
					retry = false;
				}
				catch (InterruptedException e)
				{
				}
			}
		}

		@Override
		public void onTouchEvent(MotionEvent event)
		{
			super.onTouchEvent(event);
			painting.doTouchEvent(event);
		}

	}

}
