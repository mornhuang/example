package mobile.android.ch21.jni.san.angeles;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

public class DemoActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mGLView = new DemoGLSurfaceView(this);
		setContentView(mGLView);
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		mGLView.onResume();
	}

	private GLSurfaceView mGLView;

	static
	{
		System.loadLibrary("sanangeles");
	}
}

class DemoGLSurfaceView extends GLSurfaceView
{
	public DemoGLSurfaceView(Context context)
	{
		super(context);
		mRenderer = new MyRenderer();
		setRenderer(mRenderer);
	}

	public boolean onTouchEvent(final MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			nativePause();
		}
		return true;
	}

	MyRenderer mRenderer;

	private static native void nativePause();
}


