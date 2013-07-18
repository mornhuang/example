package mobile.android.ch19.rotate.cube;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Main extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
 
		GLSurfaceView glView = new GLSurfaceView(this);
		MyRender myRender = new MyRender();
		glView.setRenderer(myRender);

		setContentView(glView);
		

	}
}