package mobile.android.ch05.edittext.face;

import java.lang.reflect.Field;
import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;

public class Main extends Activity
{
	private EditText edittext;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		edittext = (EditText) findViewById(R.id.edittext);
	}

	public void onClick_RandomFace(View view)
	{
		int randomId = 1 + new Random().nextInt(9);
		try
		{
			Field field = R.drawable.class.getDeclaredField("face" + randomId);

			int resourceId = Integer.parseInt(field.get(null).toString());
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					resourceId);
			ImageSpan imageSpan = new ImageSpan(this, bitmap);
			SpannableString spannableString = new SpannableString("face");
			spannableString.setSpan(imageSpan, 0, 4,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			edittext.append(spannableString);

		}
		catch (Exception e)
		{
			
		}
	}
	
}