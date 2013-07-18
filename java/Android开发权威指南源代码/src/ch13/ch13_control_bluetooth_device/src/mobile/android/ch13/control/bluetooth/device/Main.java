package mobile.android.ch13.control.bluetooth.device;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_Enable_Bluetooth(View view)
	{

		 Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		 startActivityForResult(enableIntent, 1);
		//BluetoothAdapter bluetoothAdapter = BluetoothAdapter
		//		.getDefaultAdapter();

	//	bluetoothAdapter.enable();

	}

	public void onClick_Disable_Bluetooth(View view)
	{
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();

		bluetoothAdapter.disable();

	}

}