package mobile.android.ch20.combination.rotate;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Main extends Activity
{
	private GLSurfaceView mGLSurfaceView;

	static void checkGLError(GL gl)
	{
		int error = ((GL10) gl).glGetError();
		if (error != GL10.GL_NO_ERROR)
		{
			throw new RuntimeException("GLError 0x"
					+ Integer.toHexString(error));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mGLSurfaceView = new GLSurfaceView(this);
		mGLSurfaceView.setRenderer(new MyRenderer());
		setContentView(mGLSurfaceView);
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
}
