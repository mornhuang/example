package mobile.android.ch14.record.video;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Main extends Activity implements OnClickListener
{
	public VideoView videoView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		videoView = (VideoView) findViewById(R.id.videoview);
		Button btnTakePicture = (Button) findViewById(R.id.btnTakePicture);
		btnTakePicture.setOnClickListener(this);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == 1)
		{
			if (resultCode == Activity.RESULT_OK)
			{
				Uri uri = data.getData();
				Cursor cursor = this.getContentResolver().query(uri, null,
						null, null, null);
				if (cursor.moveToFirst())
				{

					String videoPath = cursor.getString(cursor
							.getColumnIndex("_data"));
					videoView.setVideoURI(Uri.parse(videoPath));
					// 设置视频控制器
					videoView.setMediaController(new MediaController(this));
					// 开始播放视频
					videoView.start();

				}

			}

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View view)
	{
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		startActivityForResult(intent, 1);

	}

}
