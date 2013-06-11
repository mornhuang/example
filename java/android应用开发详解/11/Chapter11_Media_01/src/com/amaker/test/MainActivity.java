package com.amaker.test;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	private static final String PATH = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		/*String path = "http://ms1.m.mop.com/2008/08/27/012/12198150127399164.mp3";
		Uri uri = Uri.parse(path);
		MediaPlayer player = MediaPlayer.create(this, uri);
		player.start();
		*/
		MediaPlayer mp = new MediaPlayer();
		String path = "http://ms1.m.mop.com/2008/08/27/012/12198150127399164.mp3";
		try {
			mp.setDataSource(path);
			mp.prepare();
			mp.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * MediaPlayer player = MediaPlayer.create(this, R.raw.test);
		 * player.start();
		 */

		/*MediaPlayer mp = new MediaPlayer();
		String path = "/sdcard/test.mp3";
		try {
			mp.setDataSource(path);
			mp.prepare();
			mp.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	}
}