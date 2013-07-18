package mobile.android.ch15.game.surfaceview;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		GameSurfaceView gameSurfaceView = new GameSurfaceView(this);
		gameSurfaceView.x = getWindowManager().getDefaultDisplay().getWidth() / 2;
		gameSurfaceView.y = getWindowManager().getDefaultDisplay().getHeight() / 2;
	
		setContentView(gameSurfaceView, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
	
    }
}