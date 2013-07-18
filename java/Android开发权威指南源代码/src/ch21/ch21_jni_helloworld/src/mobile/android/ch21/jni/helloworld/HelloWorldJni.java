package mobile.android.ch21.jni.helloworld;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;

public class HelloWorldJni extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		TextView tv = new TextView(this);
		tv.setText(stringFromJNI());
		setContentView(tv);		
	}

	public native String stringFromJNI();

	static
	{
		System.loadLibrary("hello-jni");
	}
}
