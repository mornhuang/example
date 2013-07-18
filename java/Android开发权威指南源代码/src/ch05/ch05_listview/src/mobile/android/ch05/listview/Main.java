package mobile.android.ch05.listview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class Main extends Activity implements OnItemSelectedListener,
		OnItemClickListener
{
	private static String[] data = new String[]
	{
			"天地逃生",
			"保持通话",
			"乱世佳人(飘)",
			"怪侠一枝梅",
			"第五空间",
			"孔雀翎",
			"变形金刚3（真人版）",
			"星际传奇",
			"《大笑江湖》剧中，小鞋匠是小沈阳，他常强出头，由不懂武功的菜鸟变成武林第一高手；赵本山则是个武功高强的大盗，被不会武功的小沈阳打败；程野扮演赵本山的手下皮丘，经常拖累赵本山。 其余角色都围绕小沈阳设置。" };

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		Log.d("itemclick", "click " + position + " item");

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id)
	{
		Log.d("itemselected", "select " + position + " item");
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent)
	{
		Log.d("nothingselected", "nothing selected");

	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView lvCommonListView = (ListView) findViewById(R.id.lvCommonListView);

		// 将ArrayAdapter构造方法的最后一个参数改成dataList，系统就会加载List对象的数据
		// List<String> dataList = new ArrayList<String>();
		// dataList.add("机器化身");
		// dataList.add("火星任务");
		ArrayAdapter<String> aaData = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);

		lvCommonListView.setAdapter(aaData);
	//	lvCommonListView.setSelection(6);
		lvCommonListView.setOnItemClickListener(this);
		lvCommonListView.setOnItemSelectedListener(this);

	}

}
