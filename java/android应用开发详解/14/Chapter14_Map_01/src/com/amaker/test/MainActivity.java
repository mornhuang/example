package com.amaker.test;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
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
        tv = (TextView)findViewById(R.id.myTextView01);
        locate();
        
       /* LocationManager locationManager = (LocationManager) 
        	getSystemService(Context.LOCATION_SERVICE);
        
        String name = LocationManager.GPS_PROVIDER;
        String name1 = LocationManager.NETWORK_PROVIDER;
        LocationProvider myProvider;
        myProvider = locationManager.getProvider(name);
        
        boolean enabledOnly = true;
        List<String> providers = locationManager.getProviders(enabledOnly);*/
        
     /*   Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        
        // 获得符合条件最好的Provider
        String bestProvider = locationManager.getBestProvider(criteria, true);
        // 获得符合条件的Provider
        List<String> matchingProviders = locationManager.getProviders(criteria,
        		false);*/
        
        
        
 
        
    }
    
    private void locate(){
    	 StringBuilder builder = new StringBuilder("可利用的providers:");
         List<String> providers = locationManager.getProviders(true);
         
         LocationListener ll = new LocationListener(){
			@Override
			public void onLocationChanged(Location location) {
				
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
         
         for(String provider:providers){
        	 locationManager.requestLocationUpdates(provider, 0, 1000, ll );
        	 builder.append(provider).append("\n");
        	 Location location = locationManager.getLastKnownLocation(provider);
        	 if(location!=null){
        		 double lat = location.getLatitude();
        		 double lng = location.getLongitude();
        		 builder.append("(");
        		 builder.append(lat);
        		 builder.append(",");
        		 builder.append(lng);
        		 builder.append(")");
        	 }else{
        		 builder.append("没有位置信息");
        	 }
         }
         tv.setText(builder);
    }
}