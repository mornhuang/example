package com.amaker.wlo;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.amaker.util.HttpUtil;

public class MainMenuActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("掌中宝无线订餐系统-主菜单");
        setContentView(R.layout.main_menu);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
    }
    
    // 继承BaseAdapter
    public class ImageAdapter extends BaseAdapter {
    	// 上下文
        private Context mContext;
        // 构造方法
        public ImageAdapter(Context c) {
            mContext = c;
        }
        // 组件个数
        public int getCount() {
            return mThumbIds.length;
        }
        // 当前组件
        public Object getItem(int position) {
            return null;
        }
        // 当前组件id
        public long getItemId(int position) {
            return 0;
        }
        // 获得当前视图
        public View getView(int position, View convertView, ViewGroup parent) {
        	// 声明图片视图
            ImageView imageView;
            if (convertView == null) {
            	// 实例化图片视图
                imageView = new ImageView(mContext);
                // 设置图片视图属性
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            // 设置图片视图图片资源
            imageView.setImageResource(mThumbIds[position]);
            // 为当前视图添加监听器
            switch (position) {
			case 0:
				// 添加点餐监听器
				imageView.setOnClickListener(orderLinstener);
				break;
			case 1:
				// 并台监听器
				imageView.setOnClickListener(unionTableLinstener);
				break;
			case 2:
				// 添加转台监听器
				imageView.setOnClickListener(changeTableLinstener);
				break;
			case 3:
				// 添加查台监听器
				imageView.setOnClickListener(checkTableLinstener);
				break;
			case 4:
				// 添加更新监听器
				imageView.setOnClickListener(updateLinstener);
				break;
			case 6:
				// 添加注销监听器
				imageView.setOnClickListener(exitLinstener);
				break;
			case 7:
				// 添加结算监听器
				imageView.setOnClickListener(payLinstener);
				break;
			default:
				break;
			}
            
            return imageView;
        }
        // 图片资源数组
        private Integer[] mThumbIds = {
                R.drawable.diancai, R.drawable.bingtai,
                R.drawable.zhuantai, R.drawable.chatai,
                R.drawable.gengxin, R.drawable.shezhi,
                R.drawable.zhuxiao, R.drawable.jietai
        };
    }
    
    // 更新监听器
    OnClickListener updateLinstener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动结算Activity
			intent.setClass(MainMenuActivity.this, UpdateActivity.class);
			startActivity(intent);
		}
	};
    
    // 查台监听器
    OnClickListener checkTableLinstener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动结算Activity
			intent.setClass(MainMenuActivity.this, CheckTableActivity.class);
			startActivity(intent);
		}
	};
    
    // 结算监听器
    OnClickListener payLinstener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动结算Activity
			intent.setClass(MainMenuActivity.this, PayActivity.class);
			startActivity(intent);
		}
	};
    
    // 订餐监听器
    OnClickListener orderLinstener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// 启动订餐Activity
			intent.setClass(MainMenuActivity.this, OrderActivity.class);
			startActivity(intent);
		}
	};
	// 注销监听器
    OnClickListener exitLinstener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			logout();
		}
	};
	
	// 转台监听器
    OnClickListener changeTableLinstener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			changeTable();
		}
	};
	
	// 并台监听器
    OnClickListener unionTableLinstener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			unionTable();
		}
	};
	
	
	// 换台系统
	private void changeTable(){
		// 获得LayoutInflater实例
		LayoutInflater inflater = LayoutInflater.from(this);
		// 获得LinearLayout视图实例
		View v =inflater.inflate(R.layout.change_table, null);
		// 从LinearLayout中获得EditText实例
		final EditText et1 = (EditText) v.findViewById(R.id.change_table_order_number_EditText);
		final EditText et2 = (EditText) v.findViewById(R.id.change_table_no_EditText);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(" 真的要换桌位吗？")
		       .setCancelable(false)
		       .setView(v)
		       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	// 获得订单号
		        	String orderId = et1.getText().toString();
		        	// 获得桌号
		       		String tableId = et2.getText().toString();
		       		// 查询参数
		       		String queryString = "orderId="+orderId+"&tableId="+tableId;
		       		// url
		       		String url = HttpUtil.BASE_URL+"servlet/ChangeTableServlet?"+queryString;
		       		// 查询返回结果
		       		String result = HttpUtil.queryStringForPost(url);
		       		// 显示结果
		       		Toast.makeText(MainMenuActivity.this,result,Toast.LENGTH_LONG).show();
		           }
		       })
		       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	// 并台系统
	private void unionTable(){
		// 实例化LayoutInflater
		LayoutInflater inflater = LayoutInflater.from(this);
		// 获得自定义视图
		View v =inflater.inflate(R.layout.union_table, null);
		// 获得Spinner
		final Spinner spinner1 = (Spinner) v.findViewById(R.id.union_table_Spinner1);
		final Spinner spinner2 = (Spinner) v.findViewById(R.id.union_table_Spinner2);
		// 访问服务器的URL
		String urlStr = HttpUtil.BASE_URL + "servlet/UnionTableServlet";
		try {
			// 实例化URL
			URL url = new URL(urlStr);
			// URLConnection 实例
			URLConnection conn = url.openConnection();
			// 获得输入流
			InputStream in = conn.getInputStream();
			// 获得DocumentBuilderFactory对象
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// 获得DocumentBuilder对象
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 获得Document对象
			Document doc = builder.parse(in);
			// 获得节点列表
			NodeList nl = doc.getElementsByTagName("table");
			// Spinner数据
			List items = new ArrayList();
			
			// 获得XML数据
			for (int i = 0; i < nl.getLength(); i++) {
				// 桌位编号
				String id = doc.getElementsByTagName("id")
						.item(i).getFirstChild().getNodeValue();
				// 桌号
				int num = Integer.parseInt(doc.getElementsByTagName("num")
						.item(i).getFirstChild().getNodeValue());
				Map data = new HashMap();
				data.put("id", id);
				items.add(data);
			}
			
			// 获得SpinnerAdapter
			SpinnerAdapter as = new 
			SimpleAdapter(this, items, 
					android.R.layout.simple_spinner_item,
					new String[] { "id" }, new int[] { android.R.id.text1 });
			
			// 绑定数据
			spinner1.setAdapter(as);
			spinner2.setAdapter(as);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(" 真的要并桌吗？")
		       .setCancelable(false)
		       .setView(v)
		       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   TextView tv1 = (TextView) spinner1.getSelectedView();
		        	   TextView tv2 = (TextView) spinner2.getSelectedView();
		        	   
		        	   String tableId1 = tv1.getText().toString();
		        	   String tableId2 = tv2.getText().toString();
		        		// 查询参数
		       			String queryString = "tableId1="+tableId1+"&tableId2="+tableId2;
		       			// url
		       			String url = HttpUtil.BASE_URL+"servlet/UnionTableServlet2?"+queryString;
		       			// 查询返回结果
		       			String result =  HttpUtil.queryStringForPost(url);
		           }
		       })
		       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	// 退出系统
	private void logout(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("真的要退出系统吗？")
		       .setCancelable(false)
		       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	  SharedPreferences pres = getSharedPreferences("user_msg", MODE_WORLD_WRITEABLE);
		        	  SharedPreferences.Editor editor = pres.edit();
		        	  editor.putString("id", "");
		        	  editor.putString("name", "");
		        	  Intent intent = new Intent();
		        	  intent.setClass(MainMenuActivity.this, LoginActivity.class);
		        	  startActivity(intent);
		           }
		       })
		       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
}