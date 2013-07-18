package mobile.android.ch21.jni.nativeaudio;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NativeAudio extends Activity
{

	static final int CLIP_PLAYBACK = 1;
	static AssetManager assetManager;

	static boolean isPlayingAsset = false;
	static boolean isPlayingUri = false;

	@Override
	protected void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.main);

		assetManager = getAssets();

		createEngine();
		createBufferQueueAudioPlayer();

		((Button) findViewById(R.id.embedded_soundtrack))
				.setOnClickListener(new OnClickListener()
				{
					boolean created = false;

					public void onClick(View view)
					{
						if (!created)
						{
							created = createAssetAudioPlayer(assetManager,
									"background.mp3");
						}
						if (created)
						{
							isPlayingAsset = !isPlayingAsset;
							setPlayingAssetAudioPlayer(isPlayingAsset);
						}
					}
				});

		((Button) findViewById(R.id.record))
				.setOnClickListener(new OnClickListener()
				{
					boolean created = false;

					public void onClick(View view)
					{
						if (!created)
						{
							created = createAudioRecorder();
						}
						if (created)
						{
							startRecording();
						}
					}
				});

		((Button) findViewById(R.id.playback))
				.setOnClickListener(new OnClickListener()
				{
					public void onClick(View view)
					{

						playback();
					}
				});

	}

	@Override
	protected void onPause()
	{

		isPlayingAsset = false;
		setPlayingAssetAudioPlayer(false);
		isPlayingUri = false;
		setPlayingUriAudioPlayer(false);
		super.onPause();
	}

	@Override
	protected void onDestroy()
	{
		shutdown();
		super.onDestroy();
	}

	public static native void createEngine();

	public static native void createBufferQueueAudioPlayer();

	public static native boolean createAssetAudioPlayer(
			AssetManager assetManager, String filename);

	public static native void setPlayingAssetAudioPlayer(boolean isPlaying);

	public static native boolean createUriAudioPlayer(String uri);

	public static native void setPlayingUriAudioPlayer(boolean isPlaying);

	public static native boolean playback();


	public static native boolean createAudioRecorder();

	public static native void startRecording();

	public static native void shutdown();

	static
	{
		System.loadLibrary("native-audio-jni");
	}

}
