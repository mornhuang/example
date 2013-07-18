package mobile.android.ch05.text_background;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

public class Main extends Activity
{
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TextView textview = (TextView) findViewById(R.id.textview);
		String text = "<Ã»ÓÐ±³¾°><»ÆÉ«±³¾°>\n\n<À¶É«±³¾°£¬ºìÉ«ÎÄ×Ö>";
		SpannableString spannableString = new SpannableString(text);

		int start = 6;
		int end = 12;
	
		BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(
				Color.YELLOW);
		spannableString.setSpan(backgroundColorSpan, start, end,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		start = 14;
		ColorSpan colorSpan = new ColorSpan(Color.RED, Color.BLUE);
		spannableString.setSpan(colorSpan, start, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		textview.setText(spannableString);

	}
}