package mobile.android.ch07.popupwindow.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

public class Main extends Activity
{
	private PopupWindow popupWindow;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	public void onClick_ShowToast(View v)
	{
		View layout = getLayoutInflater().inflate(R.layout.toast, null);        
        popupWindow = new PopupWindow(layout, 200, 100);
        popupWindow.setTouchable(false);        
        popupWindow.showAtLocation(layout, Gravity.CENTER_HORIZONTAL, 20, 0);
        
	}
	
	public void onClick_CloseToast(View v) 
	{
		popupWindow.dismiss();
        
	}	
}