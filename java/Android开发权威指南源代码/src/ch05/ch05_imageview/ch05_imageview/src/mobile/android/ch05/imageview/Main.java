package mobile.android.ch05.imageview;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageView imageView = (ImageView) findViewById(R.id.imageview);
		imageView.setLayoutParams(new LinearLayout.LayoutParams(200, 100));
		setTitle("height:" + imageView.getLayoutParams().width + "  height:"
				+ imageView.getLayoutParams().height);

	}

}