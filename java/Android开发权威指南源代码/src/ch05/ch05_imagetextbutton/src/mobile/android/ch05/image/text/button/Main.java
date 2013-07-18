package mobile.android.ch05.image.text.button;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.Button;

public class Main extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button = (Button) findViewById(R.id.button);

		SpannableString spannableStringLeft = new SpannableString("left");
		Bitmap bitmapLeft = BitmapFactory.decodeResource(getResources(),
				R.drawable.image_left);
		ImageSpan imageSpanLeft = new ImageSpan(bitmapLeft, DynamicDrawableSpan.ALIGN_BOTTOM);
		spannableStringLeft.setSpan(imageSpanLeft, 0, 4,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	

		SpannableString spannableStringRight = new SpannableString("right");
		Bitmap bitmapRight = BitmapFactory.decodeResource(getResources(),
				R.drawable.image_right);
		ImageSpan imageSpanRight = new ImageSpan(bitmapRight);
		spannableStringRight.setSpan(imageSpanRight, 0, 5,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		button.append(spannableStringLeft);
		button.append("ÎÒµÄ°´Å¥");
		button.append(spannableStringRight);
		
		
	}
}