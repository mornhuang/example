package mobile.android.ch19.rotate.triangle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.ETC1Util;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

public class Main extends Activity
{

	private final static boolean USE_STREAM_IO = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mGLView = new GLSurfaceView(this);
		mGLView.setEGLConfigChooser(false);
		MyRenderer.TextureLoader loader;

		loader = new CompressedTextureLoader();

		mGLView.setRenderer(new MyRenderer(this, loader));
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

	private class CompressedTextureLoader implements MyRenderer.TextureLoader
	{
		public void load(GL10 gl)
		{

			InputStream input = getResources().openRawResource(R.raw.androids);
			try
			{
				ETC1Util.loadTexture(GLES10.GL_TEXTURE_2D, 0, 0, GLES10.GL_RGB,
						GLES10.GL_UNSIGNED_SHORT_5_6_5, input);
			}
			catch (IOException e)
			{

			}
			finally
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{

				}
			}
		}
	}

	private GLSurfaceView mGLView;
}
