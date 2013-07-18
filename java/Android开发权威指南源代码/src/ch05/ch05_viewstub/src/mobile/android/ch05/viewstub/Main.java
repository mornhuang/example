package mobile.android.ch05.viewstub;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

public class Main extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_Button(View v)
	{
		View view = findViewById(R.id.viewstub);
		if (view != null)
		{
			//view = ((ViewStub) view).inflate();
			((ViewStub)view).setVisibility(View.VISIBLE);
			
		}
		else
		{
			setTitle("view is null");
		}
	}
}