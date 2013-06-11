package com.amaker.test;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
/**
 * 
 * @author 郭宏志
 * 选项菜单实例
 */
public class MainActivity extends Activity {
	
	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST+1;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	/**
	 * 覆盖该方法添加子菜单项
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu file = menu.addSubMenu("文件");
		SubMenu edit = menu.addSubMenu("編輯");
		file.add(0, ITEM1, 0, "新建");
		file.add(0, ITEM2, 0, "打开");
		return true;
	}
    
    /**
     * 覆盖该方法，响应菜单选项被单击事件
     */
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case ITEM1:
			setTitle("新建文件！");
			break;
		case ITEM2:
			setTitle("打开文件！");
			break;
		}
    	return true;
    }
}