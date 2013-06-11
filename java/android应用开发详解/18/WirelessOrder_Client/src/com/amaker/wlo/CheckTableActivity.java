package com.amaker.wlo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaker.util.CheckTable;
import com.amaker.util.HttpUtil;
/**
 * 查台
 * @author 郭宏志
 */
public class CheckTableActivity extends Activity{
	// 显示餐桌状态的GridView
	private GridView gv;
	// 餐桌数量
	private int count;
	// 保存餐桌信息的列表
	private List list = new ArrayList();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("掌中宝无线订餐系统-查台");
		// 设置当前Activity的界面布局
		setContentView(R.layout.check_table);
		// 实例化
        gv = (GridView) findViewById(R.id.check_table_gridview);
        //获得餐桌列表
        getTableList();
        // 为GridView绑定数据
        gv.setAdapter(new ImageAdapter(this));
	}
	
	// 获得餐桌信息列表，信息包括桌号和状态
	private void getTableList(){
		// 访问服务器url
		String url = HttpUtil.BASE_URL+"servlet/CheckTableServlet";
		// 查询返回结果
		String result = HttpUtil.queryStringForPost(url);
		// 拆分字符串，转换成对象，添加到列表
		String[] strs = result.split(";");
		for (int i = 0; i < strs.length; i++) {
			int idx = strs[i].indexOf(",");
			int num = Integer.parseInt(strs[i].substring(0, idx));
			int flag = Integer.parseInt(strs[i].substring(idx+1));
			CheckTable ct = new CheckTable();
			ct.setFlag(flag);
			ct.setNum(num);
			list.add(ct);
		}
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
            return list.size();
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
        	LayoutInflater inflater = 
        		LayoutInflater.from(CheckTableActivity.this);
        	View v = null;
        	ImageView imageView =null;
        	TextView tv =null;
            if (convertView == null) {
            	// 实例化图片视图
            	v = inflater.inflate(R.layout.check_table_view,null);
            	// 设置图片视图属性
                v.setPadding(8, 8, 8, 8);
            } else {
                v = (View) convertView;
            }
            // 获得ImageView对象
            imageView = (ImageView) v.findViewById(R.id.check_table_ImageView01);
       	 	// 获得TextView对象
            tv = (TextView) v.findViewById(R.id.check_tableTextView01);
            // 获得CheckTable对象
            CheckTable ct = (CheckTable) list.get(position);
            if(ct.getFlag()==1){
            	// 设置ImageView图片为 有人
            	imageView.setImageResource(R.drawable.youren);
            }else{
            	// 设置ImageView图片为 空位
            	imageView.setImageResource(R.drawable.kongwei);
            }
            // 为TextView设置桌号
            tv.setText(ct.getNum()+"");
            return v;
        }
    }
}
