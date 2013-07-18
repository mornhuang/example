package mobile.android.ch15.game.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback, OnTouchListener
{
	public float x;
	public float y;

	private SurfaceHolder surfaceHolder;

	public GameSurfaceView(Context context)
	{
		super(context);
		setOnTouchListener(this);

		surfaceHolder = this.getHolder();

		surfaceHolder.addCallback(this);
		this.setFocusable(true);

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		drawCircle();

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouch(View view, MotionEvent event)
	{
		AnimThread animThread = new AnimThread( event.getX(), event.getY());
		Thread thread = new Thread(animThread);
		thread.start();
		return false;
	}

	class AnimThread implements Runnable
	{
		private float newX, newY;



		public AnimThread(float newX, float newY)
		{
			this.newX = newX;
			this.newY = newY;
	

		}

		@Override
		public void run()
		{
			float scale = Math.abs(newY - y) / Math.abs(newX - x);

			while (newX != x && newY != y)
			{
				if (newX > x)
				{
					x += 1;
					if (newX < x)
						x = newX;
				}
				else if (newX < x)
				{
					x -= 1;
					if (newX > x)
						x = newX;

				}
				if (newY > y)
				{
					y += scale;
					if (newY < y)
						y = newY;
				}
				else if (newY < y)
				{
					y -= scale;
					if (newY > y)
						y = newY;
				}
				try
				{
					drawCircle();
					Thread.sleep(50);

				}
				catch (Exception e)
				{

				}
			}

		}

	}

	public void drawCircle()
	{
		if (surfaceHolder == null)
			return;
	
		Canvas canvas = surfaceHolder.lockCanvas();
		canvas.drawColor(Color.BLACK);
		if (canvas == null)
			return;
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		canvas.drawCircle(x, y, 20, paint);
		surfaceHolder.unlockCanvasAndPost(canvas);
	}
}
