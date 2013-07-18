package mobile.android.ch04.show.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button button1 = (Button)findViewById(R.id.button1);
		Button button2 = (Button)findViewById(R.id.button2);
		Button button3 = (Button)findViewById(R.id.button3);
		
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		Intent intent = null;
		switch (view.getId())
		{
			case R.id.button1:	
				intent = new Intent(this, MyActivity1.class);
				startActivity(intent);
				break;
			case R.id.button2:			
				intent = new Intent("myaction1");
				startActivity(intent);
				break;
			case R.id.button3:
				intent = new Intent("myaction2");
				
				intent.addCategory("mycategory");
				startActivity(intent);
				
				break;
		}
		
	}
	
}