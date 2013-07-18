package mobile.android.ch18.simple.inputmethod;

import android.inputmethodservice.InputMethodService;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.Toast;

public class AndroidInputMethodService extends InputMethodService implements
		OnClickListener
{
    
	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.d("IME_onCreate", "invoked");
	}

	@Override
	public View onCreateInputView()
	{
		View view = getLayoutInflater().inflate(R.layout.keyboard, null);
		view.findViewById(R.id.button1).setOnClickListener(this);
		view.findViewById(R.id.button2).setOnClickListener(this);
		view.findViewById(R.id.button3).setOnClickListener(this);
		view.findViewById(R.id.button4).setOnClickListener(this);
		view.findViewById(R.id.btnHide).setOnClickListener(this);
		Log.d("IME_onCreateInputView", "invoked");
		return view;
	}

	@Override
	public View onCreateCandidatesView()
	{
		View view = getLayoutInflater().inflate(R.layout.keyboard, null);
		view.findViewById(R.id.button1).setOnClickListener(this);
		view.findViewById(R.id.button2).setVisibility(View.GONE);
		view.findViewById(R.id.button3).setVisibility(View.GONE);
		view.findViewById(R.id.button4).setVisibility(View.GONE);
		view.findViewById(R.id.btnHide).setVisibility(View.GONE);
		Log.d("IME_onCreateCandidatesView", "invoked");
		return view;
	}

	@Override
	public void onStartInputView(EditorInfo info, boolean restarting)
	{
		Log.d("IME_onStartInputView", "invoked");
		super.onStartInputView(info, restarting);
	}

	@Override
	public void onFinishInput()
	{
		Log.d("IME_onFinishInput", "invoked");
		super.onFinishInput();
	}

	@Override
	public void onDestroy()
	{
		Log.d("IME_onDestroy", "invoked");
		super.onDestroy();
	}
    
	@Override
	public void onClick(View view)
	{
		if (view.getId() == R.id.btnHide)
		{
			hideWindow();

		}
		else
		{
			Button button = (Button) view;
			InputConnection inputConnection = getCurrentInputConnection();
			
			if (button.getId() == R.id.button1)
			{
				inputConnection.setComposingText(button.getText(), 1);								
			}
			else
			{							
				inputConnection.commitText(button.getText(), 1);
			}
		}
	}
}
