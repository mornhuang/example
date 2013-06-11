package com.amaker.wlo;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.amaker.provider.Menus;
import com.amaker.util.HttpUtil;
/**
 * 
 * @author 郭宏志
 * 实现数据同步功能
 */
public class UpdateActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("掌中宝无线订餐系统-数据同步");
		// 获得ListView实例
		ListView listView = getListView();
		// 声明ListView要绑定的数据
		String[] items = {"更新菜谱表数据[MenuTbl]", "更新餐桌表数据[TableTbl]" };
		// 实例化adapter
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		// 为ListView设置adapter
		listView.setAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		switch (position) {
		// 更新菜谱表数据
		case 0:
			confirm(1);
			break;
		// 更新桌位表数据
		case 1:
			confirm(2);
			break;
		default:
			break;
		}
	}
	// 确认对话框
	private void confirm(final int item) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("你真的要更新吗?").setCancelable(false).setPositiveButton(
				"确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						if (item == 1) {
							// 更新菜谱表数据
							updateMenu();
						} else {
							// 更新桌位表数据
							updateTable();
						}
					}
				}).setNegativeButton("取消",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
	// 更新菜谱表
	private void updateMenu() {
		// 访问服务器url
		String urlStr = HttpUtil.BASE_URL + "servlet/UpdateServlet";
		try {
			// 实例化URL对象
			URL url = new URL(urlStr);
			// 打开连接
			URLConnection conn = url.openConnection();
			// 获得输入流
			InputStream in = conn.getInputStream();
			// 实例化DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// 实例化DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 获得Document
			Document doc = builder.parse(in);
			// 获得节点列表
			NodeList nl = doc.getElementsByTagName("menu");
			// 获得访问数据接口ContentResolver
			ContentResolver cr = getContentResolver();
			// 访问数据的Uri
			Uri uri1 = Menus.CONTENT_URI;
			// 删除本地SQLite数据库中菜谱表中的数据
			cr.delete(uri1, null, null);

			// 循环将数据保存到菜谱表
			for (int i = 0; i < nl.getLength(); i++) {
				// 实例化ContentValues
				ContentValues values = new ContentValues();
				// 解析XML文件获得菜单id
				int id = Integer.parseInt(doc.getElementsByTagName("id")
						.item(i).getFirstChild().getNodeValue());
				// 名称
				String name = doc.getElementsByTagName("name").item(i)
						.getFirstChild().getNodeValue();
				// 图片路径
				String pic = doc.getElementsByTagName("pic").item(i)
						.getFirstChild().getNodeValue();
				// 价格
				int price = Integer.parseInt(doc.getElementsByTagName("price")
						.item(i).getFirstChild().getNodeValue());
				// 分类编号
				int typeId = Integer.parseInt(doc
						.getElementsByTagName("typeId").item(i).getFirstChild()
						.getNodeValue());
				// 备注
				String remark = doc.getElementsByTagName("remark").item(i)
						.getFirstChild().getNodeValue();
				
				// 添加到ContenValues对象
				values.put("_id", id);
				values.put("name", name);
				values.put("price", price);
				values.put("pic", pic);
				values.put("typeId", typeId);
				values.put("remark", remark);
				// 插入到数据库
				cr.insert(uri1, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateTable() {

	}
}
