package mobile.android.ch07.single.choice.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity
{
	private String[] provinces = new String[]
	{ "辽宁省", "山东省", "河北省", "福建省", "广东省", "黑龙江省" };
	private int index;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


	}
	public void onClick_SingleChoiceDialog(View view)
	{
		new AlertDialog.Builder(this).setTitle("选择省份")
		.setSingleChoiceItems(provinces, -1, new OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				index = which;

			}
		}).setPositiveButton("确定", new OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				new AlertDialog.Builder(Main.this).setMessage(
						"您已经选择了： " + index + ":" + provinces[index])
						.show();

			}
		}).setNegativeButton("取消", new OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				new AlertDialog.Builder(Main.this).setMessage(
						"您什么都未选择.").show();

			}
		}).show();
	}
}