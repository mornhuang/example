package mobile.android.ch02.first;

import java.util.Random;

import mobile.android.ch02.first.CircleCanvas.CircleInfo;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class Main extends Activity
{
	private CircleCanvas mCircleCanvas;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(
				R.layout.main, null);
		mCircleCanvas = new CircleCanvas(this);
		viewGroup.addView(mCircleCanvas, new LayoutParams(
				LayoutParams.FILL_PARENT, 350));
		setContentView(viewGroup);

	}

	public void onClick_DrawRandomCircle(View view)
	{
		Random random = new Random();
		float randomX = (float) (100 + random.nextInt(100));
		float randomY = (float) (100 + random.nextInt(100));
		float randomRadius = (float) (20 + random.nextInt(40));
		int randomColor = 0;
		if (random.nextInt(100) > 50)
		{
			randomColor = Color.BLUE;
		}
		else
		{
			if (random.nextInt(100) > 50)
				randomColor = Color.RED;
			else
				randomColor = Color.GREEN;
		}
		CircleInfo circleInfo = new CircleInfo();
		circleInfo.setX(randomX);
		circleInfo.setY(randomY);
		circleInfo.setRadius(randomRadius);
		circleInfo.setColor(randomColor);
		mCircleCanvas.mCircleInfos.add(circleInfo);
		mCircleCanvas.invalidate();

		// 输出调试信息
		Log.v("VERBOSE_randomX", String.valueOf(randomX));
		Log.d("DEBUG_randomY", String.valueOf(randomY));
		Log.i("INFO_randomRadius", String.valueOf(randomRadius));
		Log.w("WARN_randomColor", String.valueOf(randomColor));
		Log.e("ERROR_area", String.valueOf(3.14 * randomRadius * randomRadius));

		Log.println(Log.VERBOSE, "VERBOSE_println", "VERBOSE_println");

	}

	public void onClick_Clear(View view)
	{
		mCircleCanvas.mCircleInfos.clear();
		mCircleCanvas.invalidate();

	}
}