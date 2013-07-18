package mobile.android.ch05.link.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView textView1 = (TextView) findViewById(R.id.textview1);
		TextView textView2 = (TextView) findViewById(R.id.textview2);

		String text1 = "œ‘ æActivity1";
		String text2 = "œ‘ æActivity2";

		SpannableString spannableString1 = new SpannableString(text1);
		SpannableString spannableString2 = new SpannableString(text2);

		spannableString1.setSpan(new ClickableSpan()
		{

			@Override
			public void onClick(View widget)
			{
				Intent intent = new Intent(Main.this, Activity1.class);
				startActivity(intent);

			}
		}, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		spannableString2.setSpan(new ClickableSpan()
		{

			@Override
			public void onClick(View widget) 
			{
				Intent intent = new Intent(Main.this, Activity2.class);
				startActivity(intent);

			}
		}, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		textView1.setText(spannableString1);
		textView2.setText(spannableString2);
		
		textView1.setMovementMethod(LinkMovementMethod.getInstance());
		textView2.setMovementMethod(LinkMovementMethod.getInstance());

	}
}