package com.amaker.test;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	private LocationManager locationManager;
	private TextView tv;
	private StringBuilder builder = new StringBuilder("位置信息:\n");
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		tv = (TextView) findViewById(R.id.myTextView01);

		String provider = LocationManager.GPS_PROVIDER;
		Location location = locationManager
				.getLastKnownLocation(provider);
		
		updateMsg(location);
		
		LocationListener ll = new LocationListener(){
			@Override
			public void onLocationChanged(Location location) {
				updateMsg(location);
			}
			@Override
			public void onProviderDisabled(String provider) {
			}

			@Override
			public void onProviderEnabled(String provider) {
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}
         };
		
		locationManager.requestLocationUpdates(provider, 2000, 10,
				ll);

	}

	private void updateMsg(Location l) {
		
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
			
			builder.append("\n");

		} else {
			builder.append("没有位置信息");
		}
		tv.setText(builder);
	}

}