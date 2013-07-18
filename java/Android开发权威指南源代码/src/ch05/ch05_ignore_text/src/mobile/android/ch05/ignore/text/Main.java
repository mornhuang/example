package mobile.android.ch05.ignore.text;

import android.app.Activity;
import android.os.Bundle;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// TextView textView1 = (TextView) findViewById(R.id.textview1);

		// textView1.setEllipsize(TextUtils.TruncateAt.END);

	}
}