package mobile.android.ch15.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

public class ShaderView extends View
{

	private Bitmap bitmap;
	private Shader bitmapShader;
	private Shader linearGradient;
	private Shader composeShader;
	private Shader radialGradient;
	private Shader sweepGradient;

	public ShaderView(Context context)
	{
		super(context);
		bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.image1);
		bitmap = Bitmap.createBitmap(bitmap, 200, 260, 200, 150);

		bitmapShader = new BitmapShader(bitmap, TileMode.REPEAT,
				TileMode.MIRROR);

		linearGradient = new LinearGradient(0, 0, 120, 120, new int[]
		{ Color.RED, Color.BLUE, Color.WHITE, Color.YELLOW }, null,
				TileMode.REPEAT);

		composeShader = new ComposeShader(bitmapShader, linearGradient,
				PorterDuff.Mode.DARKEN);

		radialGradient = new RadialGradient(60, 120, 60, new int[]
		{ Color.RED, Color.BLUE, Color.WHITE, Color.YELLOW }, null,
				TileMode.REPEAT);

		sweepGradient = new SweepGradient(300,300, new int[]
		{ Color.BLUE,Color.RED, Color.WHITE, Color.YELLOW }, null);

	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
		Paint paint = shapeDrawable.getPaint();
		paint.setShader(bitmapShader);
		shapeDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
		shapeDrawable.draw(canvas);
		shapeDrawable.setShape(new ArcShape(60, 240));
		shapeDrawable.setBounds(0, 160, 200, 310);
		shapeDrawable.draw(canvas);

		paint.setShader(linearGradient);
		canvas.drawRect(200, 0, 320, 150, paint);

		paint.setShader(composeShader);
		canvas.drawCircle(240, 240, 80, paint);

		paint.setShader(radialGradient);
		canvas.drawRect(0, 330, 120, 460, paint);

		paint.setShader(sweepGradient);
		canvas.drawRect(200, 330, 320, 460, paint);
		super.onDraw(canvas);
	}

}
