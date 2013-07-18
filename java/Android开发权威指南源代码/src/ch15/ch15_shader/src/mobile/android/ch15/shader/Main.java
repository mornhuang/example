package mobile.android.ch15.shader;

import android.app.Activity;
import android.os.Bundle;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ShaderView shaderView = new ShaderView(this);
		
		setContentView(shaderView);
	}
}