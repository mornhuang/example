package com.amaker.ch06.app;

import com.amaker.ch06.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author 郭宏志
 * 发送Email
 */
public class MainActivity extends Activity {
	// 声明视图组件
	private EditText toEditText,subjectEditText,contentEditText;
	private Button sendBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // 实例化视图组件
        toEditText = (EditText)findViewById(R.id.toEditText01);
        subjectEditText = (EditText)findViewById(R.id.subjectEditText01);
        contentEditText = (EditText)findViewById(R.id.contentEditText01);
        
        sendBtn = (Button)findViewById(R.id.sendButton01);
        // 为按钮添加单击监听器
        sendBtn.setOnClickListener(listener);
    }
    
    
    // 发送按钮单击监听器
    private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 获得输入信息
			String to = toEditText.getText().toString();
			String subject = subjectEditText.getText().toString();
			String content = contentEditText.getText().toString();
			// 创建Intent
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			// 设置内容类型
			emailIntent.setType("plain/text");
			// 设置额外信息
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{to});
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, content);
			
			startActivity(Intent.createChooser(emailIntent, "发送邮件..."));
			
		}
	};
}