package mobile.android.ch20.touch.rotate.cube;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class TouchSurfaceView extends GLSurfaceView
{

	private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
	private final float TRACKBALL_SCALE_FACTOR = 36.0f;
	private CubeRenderer mRenderer;
	private float mPreviousX;
	private float mPreviousY;

	public TouchSurfaceView(Context context)
	{
		super(context);
		mRenderer = new CubeRenderer();
		setRenderer(mRenderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

	@Override
	public boolean onTrackballEvent(MotionEvent e)
	{
		mRenderer.mAngleX += e.getX() * TRACKBALL_SCALE_FACTOR;
		mRenderer.mAngleY += e.getY() * TRACKBALL_SCALE_FACTOR;
		requestRender();
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		float x = e.getX();
		float y = e.getY();
		switch (e.getAction())
		{
			case MotionEvent.ACTION_MOVE:
				float dx = x - mPreviousX;
				float dy = y - mPreviousY;
				mRenderer.mAngleX += dx * TOUCH_SCALE_FACTOR;
				mRenderer.mAngleY += dy * TOUCH_SCALE_FACTOR;
				requestRender();
		}
		mPreviousX = x;
		mPreviousY = y;
		return true;
	}

	private class CubeRenderer implements GLSurfaceView.Renderer
	{
		private Cube mCube;
		public float mAngleX;
		public float mAngleY;

		public CubeRenderer()
		{
			mCube = new Cube();
		}

		public void onDrawFrame(GL10 gl)
		{

			gl.glClearColor(0f, 0f, 0f, 1f);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glTranslatef(0, 0, -3.0f);
			gl.glRotatef(mAngleX, 0, 1, 0);
			gl.glRotatef(mAngleY, 1, 0, 0);

			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

			mCube.draw(gl);
		}

		public void onSurfaceChanged(GL10 gl, int width, int height)
		{
			gl.glViewport(0, 0, width, height);

			float ratio = (float) width / height;
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
		}

		public void onSurfaceCreated(GL10 gl, EGLConfig config)
		{

			gl.glDisable(GL10.GL_DITHER);

			gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

			gl.glClearColor(1, 1, 1, 1);
			gl.glEnable(GL10.GL_CULL_FACE);
			gl.glShadeModel(GL10.GL_SMOOTH);
			gl.glEnable(GL10.GL_DEPTH_TEST);
		}

	}

}
