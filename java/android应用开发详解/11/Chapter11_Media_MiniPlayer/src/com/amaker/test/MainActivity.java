package com.amaker.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements MediaPlayer.OnCompletionListener {
	
	private ImageButton play,pause,stop;
	private MediaPlayer mp;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        play = (ImageButton)findViewById(R.id.play);
        pause = (ImageButton)findViewById(R.id.pause);
        stop = (ImageButton)findViewById(R.id.stop);
        
        play.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				play();
			}
		});
		
		pause.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				pause();
			}
		});
		
		stop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				stop();
			}
		});
        
		setup();
    }

    @Override
	public void onDestroy() {
		super.onDestroy();
		if (stop.isEnabled()) {
			stop();
		}
	}
	
	public void onCompletion(MediaPlayer mp) {
		stop();
	}
	
	private void play() {
		mp.start();
		
		play.setEnabled(false);
		pause.setEnabled(true);
		stop.setEnabled(true);
	}
	
	private void stop() {
		mp.stop();
		pause.setEnabled(false);
		stop.setEnabled(false);
		
		try {
			mp.prepare();
			mp.seekTo(0);
			play.setEnabled(true);
		}
		catch (Throwable t) {
			error(t);
		}
	}
	
	private void pause() {
		mp.pause();
		play.setEnabled(true);
		pause.setEnabled(false);
		stop.setEnabled(true);
	}
	
	private void loadClip() {
		try {
			mp=MediaPlayer.create(this, R.raw.test);
			mp.setOnCompletionListener(this);
		}
		catch (Throwable t) {
			error(t);
		}
	}
	
	private void setup() {
		loadClip();
		play.setEnabled(true);
		pause.setEnabled(false);
		stop.setEnabled(false);
	}
	
	private void error(Throwable t) {
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder
			.setTitle("±®¥Ì¿≤!")
			.setMessage(t.toString())
			.setPositiveButton("»∑∂®", null)
			.show();
	}
}