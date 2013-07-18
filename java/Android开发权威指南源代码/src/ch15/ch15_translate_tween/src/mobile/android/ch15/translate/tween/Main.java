package mobile.android.ch15.translate.tween;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_MoveEditText(View view)
	{
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_tween);
		animation.setRepeatCount(Animation.INFINITE);
		EditText editText = (EditText)findViewById(R.id.edittext);
		editText.startAnimation(animation);
	}
}