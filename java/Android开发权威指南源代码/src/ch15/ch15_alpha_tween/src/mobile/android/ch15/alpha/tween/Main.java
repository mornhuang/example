package mobile.android.ch15.alpha.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void onClick_Play(View view)
    {
    	ImageView imageView = (ImageView)findViewById(R.id.imageview);
    	Animation animation = AnimationUtils.loadAnimation(this,R.anim.alpha_tween);
    	imageView.startAnimation(animation);
    }
}