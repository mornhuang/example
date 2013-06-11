package com.amaker.test;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	 * 覆盖该方法添加菜单项
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, ITEM1, 0, "开始");
		menu.add(0, ITEM2, 0, "退出");
		return true;
	}
    
    /**
     * 覆盖该方法，响应菜单选项被单击事件
     */
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case ITEM1:
			setTitle("开始游戏！");
			break;
		case ITEM2:
			setTitle("退出！");
			break;
		}
    	return true;
    }
}