package mobile.android.ch17.html5.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_Demo1(View view)
	{
		Intent intent = new Intent(this, DemoActivity1.class);
		startActivity(intent);
	}
	public void onClick_Demo2(View view)
	{
		Intent intent = new Intent(this, DemoActivity2.class);
		startActivity(intent);
	}
	public void onClick_Demo3(View view)
	{
		Intent intent = new Intent(this, DemoActivity3.class);
		startActivity(intent);
	}
	public void onClick_Demo4(View view)
	{
		Intent intent = new Intent(this, DemoActivity4.class);
		startActivity(intent);
	}
	public void onClick_Demo5(View view)
	{
		Intent intent = new Intent(this, DemoActivity5.class);
		startActivity(intent);
	}
	public void onClick_Demo6(View view)
	{
		Intent intent = new Intent(this, DemoActivity6.class);
		startActivity(intent);
	}
}