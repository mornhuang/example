package com.amaker.mp.infoquery;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InfoQueryActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] menus = { "在逃人员查询", "被盗车辆查询", "人口信息查询", "社区信息查询" , "出入境资料查询", "案件查询" ,"公文查询"};
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menus));
		getListView().setTextFilterEnabled(true);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(InfoQueryActivity.this,EscapedQueryActivity.class);
			startActivity(intent);
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		}
	}
}