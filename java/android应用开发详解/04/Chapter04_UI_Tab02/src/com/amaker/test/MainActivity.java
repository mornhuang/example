package com.amaker.test;

import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

public class MainActivity extends TabActivity implements
		TabHost.TabContentFactory {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TabHost th = getTabHost();
		th.addTab(th.newTabSpec("all").setIndicator("所有通话记录").setContent(this));
		th.addTab(th.newTabSpec("ok").setIndicator("已接来电").setContent(this));
		th
				.addTab(th.newTabSpec("cancel").setIndicator("未接来电")
						.setContent(this));
	}

	public View createTabContent(String tag) {
		ListView lv = new ListView(this);
		List<String> list = new ArrayList<String>();
		list.add(tag);
		if(tag.equals("all")){
			list.add("tom");
			list.add("kite");
			list.add("rose");
		}else if(tag.equals("ok")){
			list.add("tom");
			list.add("kite");
		}else{
			list.add("rose");
		}
		
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_checked, list);
		lv.setAdapter(adapter);
		return lv;
	}
}