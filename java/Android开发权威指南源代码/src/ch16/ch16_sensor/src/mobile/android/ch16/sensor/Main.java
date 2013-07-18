package mobile.android.ch16.sensor;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main extends Activity implements SensorEventListener
{
	private TextView tvAccelerometer;
	private TextView tvMagentic;
	private TextView tvLight;
	private TextView tvOrientation;
	private TextView tvSensors;
	private SensorManager sensorManager;
	private float[] accelerometerValues;
	private float[] geomagneticValues;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);

		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
				SensorManager.SENSOR_DELAY_FASTEST);

		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
				SensorManager.SENSOR_DELAY_FASTEST);

		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_FASTEST);

		tvAccelerometer = (TextView) findViewById(R.id.tvAccelerometer);
		tvMagentic = (TextView) findViewById(R.id.tvMagentic);
		tvLight = (TextView) findViewById(R.id.tvLight);
		tvOrientation = (TextView) findViewById(R.id.tvOrientation);
		tvSensors = (TextView) findViewById(R.id.tvSensors);

		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		for (Sensor sensor : sensors)
		{
			tvSensors.append(sensor.getName() + "\n");
		}
		
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{

		switch (event.sensor.getType())
		{
			case Sensor.TYPE_ACCELEROMETER:
				String accelerometer = "加速度\n" + "X：" + event.values[0] + "\n"
						+ "Y:" + event.values[1] + "\n" + "Z:"
						+ event.values[2] + "\n";
				tvAccelerometer.setText(accelerometer);
				accelerometerValues = event.values.clone();
				break;
			case Sensor.TYPE_LIGHT:
				tvLight.setText("亮度：" + event.values[0]);

				break;
			case Sensor.TYPE_MAGNETIC_FIELD:
				String magentic = "磁场\n" + "X：" + event.values[0] + "\n" + "Y:"
						+ event.values[1] + "\n" + "Z:" + event.values[2]
						+ "\n";
				tvMagentic.setText(magentic);
				geomagneticValues = event.values.clone();
				
				break;
			
			case Sensor.TYPE_ORIENTATION:
				if (geomagneticValues != null && accelerometerValues != null)
				{
					float[] R = new float[16];
					float[] I = new float[16];
					float[] outR = new float[16];

					sensorManager.getRotationMatrix(R, I, accelerometerValues,
							geomagneticValues);
					sensorManager.remapCoordinateSystem(R,
							SensorManager.AXIS_X, SensorManager.AXIS_Y, outR);

					float[] actual_orientation = new float[3];
					actual_orientation = sensorManager.getOrientation(outR, actual_orientation);

					String orientation = "方向\n" + "X：" + event.values[0] + "\n"
							+ "Y:" + event.values[1] + "\n" + "Z:"
							+event.values[2] + "\n";
					tvOrientation.setText(orientation);
				}
				break;
			
		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		// TODO Auto-generated method stub

	}

}