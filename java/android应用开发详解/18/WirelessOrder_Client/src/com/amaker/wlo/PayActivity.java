package com.amaker.wlo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amaker.util.HttpUtil;

public class PayActivity extends Activity{
	// 显示点餐信息WebView
	private WebView wv;
	// 查询点餐信息按钮和结算按钮
	private Button queryBtn,payBtn;
	// 订单编号
	private EditText orderIdEt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置当前Activity的界面布局
		setContentView(R.layout.pay);
		// 获得WebView实例
		wv = (WebView)findViewById(R.id.pay_webview);
		// 实例化查询按钮
		queryBtn = (Button)findViewById(R.id.pay_query_Button01);
		// 实例化结算按钮
		payBtn = (Button)findViewById(R.id.pay_Button01);
		// 实例化订单编号编辑框
		orderIdEt = (EditText)findViewById(R.id.pay_order_number_EditText01);
		
		// 添加查询点餐信息监听器
		queryBtn.setOnClickListener(queryListener);
		// 添加结算息监听器
		payBtn.setOnClickListener(payListener);
	}
	
	// 查询点餐信息监听器
	OnClickListener queryListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 获得订单编号
			String orderId = orderIdEt.getText().toString();
			// 请求服务器url
			String url = HttpUtil.BASE_URL+"servlet/PayServlet?id="+orderId;
			// 将返回信息在WebView中显示
			wv.loadUrl(url);
		}
	};
	
	// 结算监听器
	OnClickListener payListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 获得订单编号
			String orderId = orderIdEt.getText().toString();
			// 请求服务器url
			String url = HttpUtil.BASE_URL+"servlet/PayMoneyServlet?id="+orderId;
			// 获得查询结果
			String result = HttpUtil.queryStringForPost(url);
			// 显示结算结果
			Toast.makeText(PayActivity.this, result, Toast.LENGTH_LONG).show();
			// 使结算按钮失效
			payBtn.setEnabled(false);
		}
	};

}
