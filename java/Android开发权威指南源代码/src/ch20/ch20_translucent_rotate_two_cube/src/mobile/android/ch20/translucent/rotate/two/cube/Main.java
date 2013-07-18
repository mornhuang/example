package mobile.android.ch20.translucent.rotate.two.cube;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Main extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLSurfaceView = new GLSurfaceView(this);
       
        mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        
        mGLSurfaceView.setRenderer(new MyRenderer(true));
       
        mGLSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(mGLSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }

    private GLSurfaceView mGLSurfaceView;
}

