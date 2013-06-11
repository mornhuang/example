package com.amaker.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainAcitivity extends Activity {
	private AutoCompleteTextView atv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		atv = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView01);
		String[] strs = { "abc", "abcd", "bcd", "bcde" };
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_dropdown_item_1line, strs);
		atv.setAdapter(adapter);
	}
}