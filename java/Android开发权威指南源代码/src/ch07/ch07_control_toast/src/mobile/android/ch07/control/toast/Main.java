package mobile.android.ch07.control.toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.app.ITransientNotification;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class Main extends Activity
{
	private ITransientNotification mITransientNotification;
    private Object obj;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		 
		setContentView(R.layout.main);
	}

	public void onClick_ShowToast(View v) 
	{		
		Toast toast = Toast.makeText(this, "ÓÀ²»ÏûÊ§µÄToast", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
		

		try
		{
			Field field = toast.getClass().getDeclaredField("mTN");
			field.setAccessible(true);
			obj = field.get(toast);
			Method method =  obj.getClass().getDeclaredMethod("show", null);
			method.invoke(obj, null);
			//mITransientNotification = (ITransientNotification) field.get(toast);
			//mITransientNotification.show();

		}
		catch (Exception e)
		{

		}
	}

	public void onClick_CloseToast(View v)
	{
		try
		{
			Method method =  obj.getClass().getDeclaredMethod("hide", null);
			method.invoke(obj, null);
			//mITransientNotification.hide();
		
			
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}
}