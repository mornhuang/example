package com.amaker.ch14.app01;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	// Intent Action 常量
	private static String PROXIMITY_ALERT_ACTION_NAME = "com.amaker.ch14.ProximityAlert";
	// 声明按钮Button实例
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前Activity的界面布局
        setContentView(R.layout.main);
        // 通过findViewById方法获得Button实例
        btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 设定
				set();
			}
		});
    }
    
    // 趋近提示方法
    private void set() {
    	// 定位服务常量
	    String locService = Context.LOCATION_SERVICE;
	    // 定位服务管理器实例
	    LocationManager locationManager;
	    // 通过getSystemService方法获得LocationManager实例
	    locationManager = (LocationManager)getSystemService(locService);
	    // 声明经度
	    double lat = 37.4;
	    // 声明纬度
	    double lng = 55.0;
	    // 声明半径(单位米)
	    float radius = 200f;
	    // 不过期
	    long expiration = -1; 
	    // 声明Intent
	    Intent intent = new Intent(PROXIMITY_ALERT_ACTION_NAME);
	    // 声明PendingIntent
	    PendingIntent pi = 
	    	PendingIntent.getBroadcast(this, -1,intent,0);
	    // 添加趋近警告
	    locationManager.addProximityAlert(lat, lng, radius, expiration,pi);
    }
}