package com.amaker.ch03.color;

import android.app.Activity;
import android.os.Bundle;
import com.amaker.test.R;
/**
 * 
 * @author 郭宏志
 *
 */
public class TestColorActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_color);
        // 引用颜色资源，设置背景色为红色
        getWindow().setBackgroundDrawableResource(R.color.red_bg);
    }
}