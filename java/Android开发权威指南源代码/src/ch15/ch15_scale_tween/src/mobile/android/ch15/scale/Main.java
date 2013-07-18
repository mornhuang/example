package mobile.android.ch15.scale;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Main extends Activity implements AnimationListener
{

	private Animation toLargeAnimation;
	private Animation toSmallAnimation;
	private ImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imageView = (ImageView) findViewById(R.id.imageview);
		toLargeAnimation = AnimationUtils.loadAnimation(this, R.anim.to_large);
		toSmallAnimation = AnimationUtils.loadAnimation(this, R.anim.to_small);
		toLargeAnimation.setAnimationListener(this);
		toSmallAnimation.setAnimationListener(this); 
		imageView.startAnimation(toSmallAnimation);
 
	}

	@Override
	public void onAnimationStart(Animation animation)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationEnd(Animation animation)
	{
		
		if (animation.hashCode() == toLargeAnimation.hashCode())
			imageView.startAnimation(toSmallAnimation);
		else
			imageView.startAnimation(toLargeAnimation);

	}

	@Override
	public void onAnimationRepeat(Animation animation)
	{
		// TODO Auto-generated method stub

	}

}