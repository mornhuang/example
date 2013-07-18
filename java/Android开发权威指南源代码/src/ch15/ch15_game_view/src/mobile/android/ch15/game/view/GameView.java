package mobile.android.ch15.game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameView extends View implements OnTouchListener
{
	public float x;
	public float y;

	public GameView(Context context)
	{
		super(context);
		setOnTouchListener(this);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		canvas.drawCircle(x, y, 20, paint);
	}

	@Override
	public boolean onTouch(View view, MotionEvent event)
	{
		AnimThread animThread = new AnimThread(this, event.getX(), event.getY());
		Thread thread = new Thread(animThread);
		thread.start();
		return false;
	}

	private Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			((View) msg.obj).invalidate();
			super.handleMessage(msg);
		}

	};

	class AnimThread implements Runnable
	{
		private float newX, newY;

		private View view;

		public AnimThread(View view, float newX, float newY)
		{
			this.newX = newX;
			this.newY = newY;
			this.view = view;

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
					Message msg = new Message();
					msg.obj = view;
					handler.sendMessage(msg);
					Thread.sleep(50);

				}
				catch (Exception e)
				{

				}
			}

		}

	}
}
