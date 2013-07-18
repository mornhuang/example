package mobile.android.ch02.first;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class CircleCanvas extends View
{
	public List<CircleInfo> mCircleInfos = new ArrayList<CircleCanvas.CircleInfo>();

	public static class CircleInfo
	{
		private float x;
		private float y;
		private float radius;
		private int color;

		public float getX()
		{
			return x;
		}

		public void setX(float x)
		{
			this.x = x;
		}

		public float getY()
		{
			return y;
		}

		public void setY(float y)
		{
			this.y = y;
		}

		public float getRadius()
		{
			return radius;
		}

		public void setRadius(float radius)
		{
			this.radius = radius;
		}

		public int getColor()
		{
			return color;
		}

		public void setColor(int color)
		{
			this.color = color;
		}

	}

	public CircleCanvas(Context context)
	{
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		for (CircleInfo circleInfo : mCircleInfos)
		{
			Paint paint = new Paint();
			paint.setColor(circleInfo.getColor());
			canvas.drawCircle(circleInfo.getX(), circleInfo.getY(),
					circleInfo.getRadius(), paint);
		}

	}

}
