package mobile.android.ch09.activity.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	public void onClick_Fade(View view)
	{
		Intent intent = new Intent(this, AnimationActivity.class);
		startActivity(intent);		
		overridePendingTransition(R.anim.fade, R.anim.fade);
		
	}
	public void onClick_Hyperspace(View view)
	{
		Intent intent = new Intent(this, AnimationActivity.class);
		startActivity(intent);		
		overridePendingTransition(R.anim.hyperspace,
				R.anim.hyperspace);
		
	}




}