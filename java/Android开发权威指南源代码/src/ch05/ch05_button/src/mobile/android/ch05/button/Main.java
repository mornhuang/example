package mobile.android.ch05.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener, OnTouchListener,
		OnFocusChangeListener, OnKeyListener
{
	private int value = 1;
	private Button imageButton;
	private Button commonButton;
	private View lastFocusview;

	@Override
	public void onClick(View view)
	{
		Button button = (Button) view;
		if (value == 1
				&& button.getWidth() == getWindowManager().getDefaultDisplay()
						.getWidth())
			value = -1;
		else if (value == -1 && button.getWidth() < 100)
			value = 1;
		button.setWidth(button.getWidth() + (int) (button.getWidth() * 0.1)
				* value);
		button.setHeight(button.getHeight() + (int) (button.getHeight() * 0.1)
				* value);
	}

	@Override
	public boolean onKey(View view, int keyCode, KeyEvent event)
	{ 
		if (KeyEvent.ACTION_DOWN == event.getAction())
		{
			view.setBackgroundResource(R.drawable.button3);
		}
		else if (KeyEvent.ACTION_UP == event.getAction())
		{
			view.setBackgroundResource(R.drawable.button2);
		}
		return false;
	}

	@Override
	public void onFocusChange(View view, boolean hasFocus)
	{
		if (hasFocus)
		{
			imageButton.setBackgroundResource(R.drawable.button2);
		}
		else
		{
			imageButton.setBackgroundResource(R.drawable.button1);
		}

	}

	@Override
	public boolean onTouch(View view, MotionEvent event)
	{

		if (event.getAction() == MotionEvent.ACTION_UP)
		{
			view.setBackgroundResource(R.drawable.button1);
		}
		else if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			view.setBackgroundResource(R.drawable.button2);
		}

		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		commonButton = (Button) findViewById(R.id.btnCommonButton);
		imageButton = (Button) findViewById(R.id.btnImageButton);
		commonButton.setOnClickListener(this);
		imageButton.setOnClickListener(this);
		imageButton.setOnTouchListener(this);
		imageButton.setOnFocusChangeListener(this);
		imageButton.setOnKeyListener(this);
	}
}