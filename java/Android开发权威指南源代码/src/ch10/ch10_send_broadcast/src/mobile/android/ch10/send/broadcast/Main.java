package mobile.android.ch10.send.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_Send_Broadcast(View view)
	{
		Intent broadcastIntent = new Intent("mobile.android.ch10.MYBROADCAST");
		broadcastIntent.addCategory("mobile.android.ch10.mycategory");
		broadcastIntent.putExtra("name", "broadcast_data");
		sendBroadcast(broadcastIntent);
		Toast.makeText(this, "广播发送成功.", Toast.LENGTH_LONG).show();
	}
}