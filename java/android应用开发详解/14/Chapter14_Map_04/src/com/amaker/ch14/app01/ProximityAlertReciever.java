package com.amaker.ch14.app01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class ProximityAlertReciever extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		// 趋近关键字
		String key = LocationManager.KEY_PROXIMITY_ENTERING;
		// 从Intent获得额外信息，判断是否进入设置区域
		Boolean isEnter = intent.getBooleanExtra(key, false);
		if(isEnter){
			Toast.makeText(context, "你已经进入海淀区！", Toast.LENGTH_LONG);
		}
	}
}
