package mobile.android.ch09.activity.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.view.View;

public class AnimationActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_activity);
		setTitle("¶¯»­Activity");
		
	}
	public void onClick_Fade(View view)
	{
		Intent intent = new Intent(this, AnimationActivity.class);
		finish();
		overridePendingTransition(R.anim.fade, R.anim.fade);
		
	}
}
