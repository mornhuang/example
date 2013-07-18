package mobile.android.ch15.frame;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Main extends Activity
{
	private AnimationDrawable animationDrawable;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		animationDrawable = (AnimationDrawable) getResources().getDrawable(
				R.anim.test);
		ImageView imageview = (ImageView) findViewById(R.id.imageview);
		imageview.setBackgroundDrawable(animationDrawable);

		

	}

	public void onClick_PlayAnim(View view)
	{
		animationDrawable.start();
		

	}

	public void onClick_StopAnim(View view)
	{
		animationDrawable.stop();
	}
}