package mobile.android.ch10.receiver.priority;

import java.util.Set;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class ShortMessageReceiver2 extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle = intent.getExtras();

		if (bundle != null)
		{
			Set<String> keys = bundle.keySet();
			for (String key : keys)
			{
				Log.d("key", key);
			}

			Object[] objArray = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[objArray.length];

			for (int i = 0; i < objArray.length; i++)
			{
				messages[i] = SmsMessage.createFromPdu((byte[]) objArray[i]);
				String s = "Receiver2\n手机号：" + messages[i].getOriginatingAddress()
						+ "\n";
				s += "短信内容：" + messages[i].getDisplayMessageBody();
				Toast.makeText(context, s, Toast.LENGTH_LONG).show();
			}

		} 
	}

}
