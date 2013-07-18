package mobile.android.ch06_ucweb.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Main extends Activity implements OnKeyListener,OnItemClickListener
{
	private PopupWindow popup;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		GridView gvPopupWindow = (GridView) getLayoutInflater().inflate(
				R.layout.popup_window, null);
		GridAdapter gridAdapter = new GridAdapter(this);
		gvPopupWindow.setAdapter(gridAdapter);
		gvPopupWindow.setOnKeyListener(this);
		gvPopupWindow.setOnItemClickListener(this);
		popup = new PopupWindow(gvPopupWindow, LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		popup.setFocusable(true);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		popup.dismiss();
		Toast.makeText(this, Const.GRID_ITEM_TEXT_LIST[position], Toast.LENGTH_LONG).show();
		
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_BACK:
				if(popup.isShowing())
					popup.dismiss();
				break;
		}
		
		return false;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add("menu");// 必须创建一项

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu)
	{
		if (popup != null)
		{
			if (popup.isShowing())
			{
				popup.dismiss();
			}
			else
			{
				View layout = getLayoutInflater().inflate(R.layout.main, null);
				popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

			}
		}
		return false;
	}
}