package mobile.android.ch05.imageswitcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class Main extends Activity implements ViewFactory, OnClickListener
{
	private ImageSwitcher imageSwitcher;

	@Override
	public View makeView()
	{
		
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundColor(0xFF000000);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

		return imageView;
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.button1:
				
				imageSwitcher.setImageResource(R.drawable.item1);
				break;

			case R.id.button2:
				imageSwitcher.setImageResource(R.drawable.item2);
				break;
			case R.id.button3:
				imageSwitcher.setImageResource(R.drawable.item3);
				break;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);

		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswitcher);
		imageSwitcher.setFactory(this);

		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));

	}
}