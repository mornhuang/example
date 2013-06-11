package com.amaker.test;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	private LocationManager locationManager;
	private TextView tv;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		tv = (TextView) findViewById(R.id.myTextView01);

		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		printMsg(location);

	}

	private void printMsg(Location l) {
		StringBuilder builder = new StringBuilder("可利用的providers:");
		
		if (l != null) {
			double lat = l.getLatitude();
			double lng = l.getLongitude();
			builder.append("(");
			builder.append(lat);
			builder.append(",");
			builder.append(lng);
			builder.append(")");
			
			if(l.hasAccuracy()){
				builder.append("\n精度：");
				builder.append(l.getAccuracy());
			}
			
			if(l.hasAltitude()){
				builder.append("\n高度：");
				builder.append(l.getAltitude());
			}
			
			if(l.hasBearing()){
				builder.append("\n方向：");
				builder.append(l.getBearing());
			}
			
			if(l.hasSpeed()){
				builder.append("\n速度：");
				builder.append(l.getSpeed());
			}

		} else {
			builder.append("没有位置信息");
		}
		tv.setText(builder);
	}

}