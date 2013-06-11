package com.amaker.wlo;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amaker.util.HttpUtil;

public class OrderActivity extends Activity {
	// 桌号下拉列表
	private Spinner tableNoSpinner;
	// 开桌、点菜和下单按钮
	private Button orderBtn, addBtn, startBtn;
	// 人数编辑框
	private EditText personNumEt;
	// 点菜列表
	private ListView lv;
	// 开桌生成的订单Id
	private String orderId;
	// 点菜列表中绑定的数据
	private List data = new ArrayList();
	// 点菜列表中具体的数据项
	private Map map;
	// ListView 的 Adapter
	private SimpleAdapter sa;
	// ListView 中显示的数据项
	private String[] from = { "id", "name","num", "price", "remark" };
	// 引用的TextView Drawable ID
	private int[] to = new int[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 为Activity设置界面布局
		setContentView(R.layout.order);
		
		// 实例化Spinner
		tableNoSpinner = (Spinner) findViewById(R.id.tableNoSpinner);
		// 为桌号下拉列表Spinner绑定数据
		setTableAdapter();
		
		// 实例化开桌按钮
		startBtn = (Button) findViewById(R.id.startButton02);
		// 为开桌按钮添加监听器
		startBtn.setOnClickListener(startListener);
		
		// 实例化点菜按钮
		addBtn = (Button) findViewById(R.id.addMealButton01);
		// 为点菜按钮添加监听器
		addBtn.setOnClickListener(addListener);
			
		// 实例化下单按钮
		orderBtn = (Button) findViewById(R.id.orderButton02);
		// 为下单按钮添加监听器
		orderBtn.setOnClickListener(orderListener);
		
		// 实例化人数编辑框
		personNumEt = (EditText) findViewById(R.id.personNumEditText02);
		
		// 实例化ListView
		lv = (ListView) findViewById(R.id.orderDetailListView01);
		// 为点菜列表ListView绑定数据
		setMenusAdapter();
	}
	
	
	// 为点菜列表ListView绑定数据
	private void setMenusAdapter(){
		// 显示点菜项的TextView引用
		to[0] = R.id.id_ListView;
		to[1] = R.id.name_ListView;
		to[2] = R.id.num_ListView;
		to[3] = R.id.price_ListView;
		to[4] = R.id.remark_ListView;
		// 实例化点菜列表ListView Adapter
		sa = new SimpleAdapter(this, data, R.layout.listview, from, to);
		// 为ListView绑定数据
		lv.setAdapter(sa);
	}
	
	
	// 为桌号下拉列表Spinner绑定数据
	private void setTableAdapter(){
		// 访问本地SQLite数据库中桌号表的Uri
	    Uri uri = Uri.parse("content://com.amaker.provider.TABLES/table");
		// 要选择桌号表中的列
		String[] projection = { "_id", "num", "description" };
		// 查询放回游标
		Cursor c = managedQuery(uri, projection, null, null, null);
		// 实例化桌号下拉列表Spinner的Adapter
		SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, c,
				new String[] { "_id" }, new int[] { android.R.id.text1 });
		// 为桌号Spinner绑定数据
		tableNoSpinner.setAdapter(adapter2);
	}
	
	
	// 开桌监听器
	private OnClickListener startListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yy:mm:dd hh:MM");
			// 开桌时间
			String orderTime = sdf.format(date);
			// 开桌用户，从登陆配置文件中获得
			SharedPreferences pres = getSharedPreferences("user_msg",
					MODE_WORLD_READABLE);
			String userId = pres.getString("id", "");
			// 桌号
			TextView tv = (TextView) tableNoSpinner.getSelectedView();
			String tableId = tv.getText().toString();
			// 人数
			String personNum = personNumEt.getText().toString();
			// 请求参数列表
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// 添加请求参数
			params.add(new BasicNameValuePair("orderTime", orderTime));
			params.add(new BasicNameValuePair("userId", userId));
			params.add(new BasicNameValuePair("tableId", tableId));
			params.add(new BasicNameValuePair("personNum", personNum));
			UrlEncodedFormEntity entity1=null;
			try {
				 entity1 =  new UrlEncodedFormEntity(params,HTTP.UTF_8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 请求服务器url
			String url = HttpUtil.BASE_URL+"servlet/StartTableServlet";
			// 获得请求对象HttpPost
			HttpPost request = HttpUtil.getHttpPost(url);
			// 设置查询参数
			request.setEntity(entity1);
			// 获得响应结果
			String result= HttpUtil.queryStringForPost(request);
			// 将开桌生成的订单Id，赋值给全局orderId
			orderId = result;
			Toast.
			makeText(OrderActivity.this, result, Toast.LENGTH_LONG).show();
		}
	};
	

	
	// 添菜监听器
	private OnClickListener addListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 调用点菜方法
			addMeal();
		}
	};
	
	//添菜方法
	private void addMeal() {
		// 获得LayoutInflater实例
		LayoutInflater inflater = LayoutInflater.from(this);
		// 实例化在弹出对话框中添加的视图
		final View v = inflater.inflate(R.layout.order_detail, null);
		// 获得视图中的Spinner对象，菜单下拉列表
		final Spinner menuSpinner = (Spinner) v.findViewById(R.id.menuSpinner);
		// 获得视图中的EditText对象,数量
		EditText numEt = (EditText) v.findViewById(R.id.numEditText);
		// 获得视图中的EditText实例，备注
		EditText remarkEt = (EditText) v.findViewById(R.id.add_remarkEditText);
		
		// 访问本地SQLite数据库中MenuTbl表的Uri
		Uri uri = Uri.parse("content://com.amaker.provider.MENUS/menu1");
		// 查询列
		String[] projection = { "_id", "name", "price" };
		// 获得ContentResolver实例
		ContentResolver cr = getContentResolver();
		// 查询放回游标
		Cursor c = cr.query(uri, projection, null, null, null);
		// 实例化SimpleCursorAdapter
		SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this,
				R.layout.spinner_lo, c,
				new String[] { "_id", "price", "name" }, new int[] {
						R.id.id_TextView01, R.id.price_TextView02,
						R.id.name_TextView03, });
		// 为点菜下拉列表Spinner绑定数据
		menuSpinner.setAdapter(adapter2);
		
		// 获得AlertDialog.Builder实例
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder
		// 设置标题
		.setMessage("请点菜：")
		// 设置自定义视图
		.setView(v)
		// 设置确定按钮
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					// 确定按钮事件
					public void onClick(DialogInterface dialog, int id) {
						
						// 获得ListView中的自定义视图LinearLayout
						LinearLayout v1 = (LinearLayout) menuSpinner
								.getSelectedView();
						
						// 获得TextView，菜编号
						TextView id_tv = (TextView) v1
								.findViewById(R.id.id_TextView01);
						// 获得TextView，菜价格
						TextView price_tv = (TextView) v1
								.findViewById(R.id.price_TextView02);
						// 获得TextView，菜名称
						TextView name_tv = (TextView) v1
								.findViewById(R.id.name_TextView03);
						// 获得EditText，菜数量
						EditText num_et = (EditText) v
								.findViewById(R.id.numEditText);
						// 获得EditText，菜备注
						EditText remark_et = (EditText) v
								.findViewById(R.id.add_remarkEditText);
						// 菜编号值
						String idStr = id_tv.getText().toString();
						// 菜价格值
						String priceStr = price_tv.getText().toString();
						// 菜名称值
						String nameStr = name_tv.getText().toString();
						// 菜数量值
						String numStr = num_et.getText().toString();
						// 菜备注值
						String remarkStr = remark_et.getText().toString();
						
						// 封装到Map中
						map = new HashMap();

						map.put("id", idStr);
						map.put("name", nameStr);
						map.put("num", numStr);
						map.put("price", priceStr);
						map.put("remark", remarkStr);
						
						// 添加到ListView
						data.add(map);
						
						// 关联的TextView
						to[0] = R.id.id_ListView;
						to[1] = R.id.name_ListView;
						to[2] = R.id.price_ListView;
						to[3] = R.id.remark_ListView;
						
						// 实例化SimpleAdapter
						sa = new SimpleAdapter(OrderActivity.this, data,
								R.layout.listview, from, to);
						// 为ListView绑定数据
						lv.setAdapter(sa);

					}
				}).setNegativeButton("取消", null);
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	
	// 下单监听器
	private OnClickListener orderListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 遍历点菜列表
			for (int i = 0; i < data.size(); i++) {
				// 获得其中点菜map
				Map map = (Map)data.get(i);
				// 获得点菜项
				String menuId = (String) map.get("id");
				String num = (String)map.get("num");
				String remark = (String)map.get("remark");
				String myOrderId = orderId;
				
				// 封装到请求参数中
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				// 添加到请求参数中
				params.add(new BasicNameValuePair("menuId", menuId));
				params.add(new BasicNameValuePair("orderId", myOrderId));
				params.add(new BasicNameValuePair("num", num));
				params.add(new BasicNameValuePair("remark", remark));
				UrlEncodedFormEntity entity1=null;
				try {
					 entity1 =  new UrlEncodedFormEntity(params,HTTP.UTF_8);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				// 请求服务器Servlet的url
				String url = HttpUtil.BASE_URL+"servlet/OrderDetailServlet";
				// 获得HttpPost对象
				HttpPost request = HttpUtil.getHttpPost(url);
				// 为请求设置参数
				request.setEntity(entity1);
				// 获得返回结果
				String result= HttpUtil.queryStringForPost(request);
				
				Toast.
				makeText(OrderActivity.this, result, Toast.LENGTH_LONG).show();
				
				finish();
			}
		}
	};
}
