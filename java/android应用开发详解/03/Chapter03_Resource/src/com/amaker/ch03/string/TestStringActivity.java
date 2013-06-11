package com.amaker.ch03.string;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.amaker.test.R;
/**
 * 
 * @author ¹ùºêÖ¾
 * ²âÊÔ×Ö·û´®×ÊÔ´
 */
public class TestStringActivity extends Activity {
	private TextView myTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.test_string);
       myTextView = (TextView)findViewById(R.id.myTextView02);
       String str = getString(R.string.test_str2).toString();
       myTextView.setText(str);
    }
}