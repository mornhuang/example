package com.amaker.ch03.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.amaker.test.R;
/**
 * @author 郭宏志
 * 测试布局资源
 */
public class TestLayoutActivity extends Activity {
	private TextView myTextView;
	private EditText myEditText;
	private Button myButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.test_layout);
       
       myTextView = (TextView)findViewById(R.id.layoutTextView01);
       myEditText = (EditText)findViewById(R.id.layoutEditText01);
       myButton = (Button)findViewById(R.id.layoutButton01);
       
    }
}