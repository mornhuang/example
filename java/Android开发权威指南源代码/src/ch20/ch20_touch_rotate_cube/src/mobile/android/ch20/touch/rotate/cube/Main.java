package mobile.android.ch20.touch.rotate.cube;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;


public class Main extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mGLSurfaceView = new TouchSurfaceView(this);
		setContentView(mGLSurfaceView);
		mGLSurfaceView.requestFocus();
		mGLSurfaceView.setFocusableInTouchMode(true);
	}

	@Override
	protected void onResume()
	{

		super.onResume();
		mGLSurfaceView.onResume();
	}

	@Override
	protected void onPause()
	{

		super.onPause();
		mGLSurfaceView.onPause();
	}

	private GLSurfaceView mGLSurfaceView;
}
