package mobile.android.ch15.rotate.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageView imageView = (ImageView)findViewById(R.id.imageview);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotate_tween);
        imageView.startAnimation(animation);

	}
}