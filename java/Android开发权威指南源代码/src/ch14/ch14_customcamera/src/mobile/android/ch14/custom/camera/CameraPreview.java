package mobile.android.ch14.custom.camera;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class CameraPreview extends Activity
{

	private Preview preview;
	private ImageView ivFocus;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		preview = new Preview(this);
		setContentView(preview);

		ivFocus = new ImageView(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN)
			preview.takePicture();
		return super.onTouchEvent(event);
	}

	public static int getPreviewDegree(Activity activity)
	{
		int rotation = activity.getWindowManager().getDefaultDisplay()
				.getRotation();
		int degree = 0;
		switch (rotation)
		{
			case Surface.ROTATION_0:
				degree = 90;
				break;
			case Surface.ROTATION_90:
				degree = 0;
				break;
			case Surface.ROTATION_180:
				degree = 270;
				break;
			case Surface.ROTATION_270:
				degree = 180;
				break;
		}
		return degree;

	}

	class Preview extends SurfaceView implements SurfaceHolder.Callback
	{
		private SurfaceHolder holder;
		private Camera camera;

		public Preview(Context context, AttributeSet attrs)
		{
			super(context, attrs);
			// TODO Auto-generated constructor stub
		}

		private PictureCallback pictureCallback = new PictureCallback()
		{

			@Override
			public void onPictureTaken(byte[] data, Camera camera)
			{

				getIntent().putExtra("bytes", data);
				setResult(20, getIntent());

				camera.stopPreview();
				camera = null;

				finish();
			}
		};

		public Preview(Context context)
		{
			super(context);

			holder = getHolder();

			holder.addCallback(this);

			holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		}

		public void surfaceCreated(SurfaceHolder holder)
		{

			camera = Camera.open();
			try
			{

				camera.setPreviewDisplay(holder);
			}
			catch (IOException exception)
			{

				camera.release();
				camera = null;
			}
		}

		public void surfaceDestroyed(SurfaceHolder holder)
		{

			camera.release();
		}

		public void surfaceChanged(final SurfaceHolder holder, int format,
				int w, int h)
		{
			try
			{
				Camera.Parameters parameters = camera.getParameters();

				parameters.setPictureFormat(PixelFormat.JPEG);

				parameters.setPreviewSize(w, h);
				parameters.setPictureSize(w, h);

				camera.setParameters(parameters);

	

				camera.setDisplayOrientation(getPreviewDegree(CameraPreview.this));

				camera.startPreview();

				ivFocus.setImageResource(R.drawable.focus1);
				LayoutParams layoutParams = new LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
				ivFocus.setScaleType(ScaleType.CENTER);
				addContentView(ivFocus, layoutParams);
				ivFocus.setVisibility(VISIBLE);

				camera.autoFocus(new AutoFocusCallback()
				{
					@Override
					public void onAutoFocus(boolean success, Camera camera)
					{
						if (success)
						{

							ivFocus.setImageResource(R.drawable.focus2);
						}
					}
				});
			}
			catch (Exception e)
			{
			}

		}

		public void takePicture()
		{
			if (camera != null)
			{

				camera.takePicture(null, null, pictureCallback);
			}
		}
	}

}