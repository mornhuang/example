package com.amaker.mp.location;

import com.amaker.mp.fileupload.FileUploadActivity;
import com.amaker.mp.infocollect.InfoCollectActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GpsLocationActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] menus = { "我的位置", "按坐标查询", "按地址查询","跟踪轨迹" };
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menus));
		getListView().setTextFilterEnabled(true);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent=null;
		switch (position) {
		case 0:
			intent = new Intent(GpsLocationActivity.this,MyPositionActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(GpsLocationActivity.this,QueryPositionByLLActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(GpsLocationActivity.this,QueryPositionByAddressActivity.class);
			startActivity(intent);
			break;
		case 3:
			//intent = new Intent(MainActivity.this,GpsAppActivity.class);
			//startActivity(intent);
			break;
		}
	}
}