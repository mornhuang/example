package mobile.android.ch05.vertical.scroll.text;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView textview = (TextView)findViewById(R.id.textview);
		textview.setMovementMethod(ScrollingMovementMethod.getInstance());
	}
}