package mobile.android.ch06.custom.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Main extends Activity
{
	private PopupWindow pop;
	private View layout;
	private int state = 2;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_MENU:
				if (state == 1)
					return false;
				layout = getLayoutInflater()
						.inflate(R.layout.menu_layout, null);
				pop = new PopupWindow(layout, getWindowManager()
						.getDefaultDisplay().getWidth(), getWindowManager()
						.getDefaultDisplay().getHeight());
				pop.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
				
				View home = layout.findViewById(R.id.home);
				home.setOnClickListener(new OnClickListener()
				{

					@Override
					public void onClick(View view)
					{
						Toast.makeText(Main.this, "单击定制菜单.", Toast.LENGTH_LONG)
								.show();
						pop.dismiss();
						state = 2;

					}
				});
				state = 1;
				return false;

			case KeyEvent.KEYCODE_BACK:
				if (state == 1)
				{
					pop.dismiss();
					state = 2;
				}
				else if (state == 2)
				{
					finish();
				}
				return false;
		}

		return super.onKeyDown(keyCode, event);
	}

}