package mobile.android.ch04.transmit.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyActivity5 extends Activity
{
	private EditText metEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myactivity5);		
		metEditText = (EditText)findViewById(R.id.edittext);			
	}
    public void onClick_Button(View view)
    {
    	String value = metEditText.getText().toString();
    	Intent intent = new Intent();
    	intent.putExtra("value", value);
    	setResult(2, intent);
    	finish();
    }
}
