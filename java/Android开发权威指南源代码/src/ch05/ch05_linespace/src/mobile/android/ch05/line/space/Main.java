package mobile.android.ch05.line.space;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView textView = (TextView)findViewById(R.id.textview);
		textView.setLineSpacing(50,1.2f);
		
	}
}