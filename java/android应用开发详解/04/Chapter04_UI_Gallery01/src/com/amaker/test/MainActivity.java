package com.amaker.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Gallery g = (Gallery) findViewById(R.id.Gallery01);
        
        g.setAdapter(new MyAdapter(this));
    }
    
    class MyAdapter extends BaseAdapter{
    	Context context;
    	MyAdapter(Context context){
    		this.context = context;
    	}
    	private Integer[] imgs = {
    			R.drawable.gallery_photo_1,
    			R.drawable.gallery_photo_2,
    			R.drawable.gallery_photo_3,
    			R.drawable.gallery_photo_4,
    			R.drawable.gallery_photo_5,
    			R.drawable.gallery_photo_6,
    			R.drawable.gallery_photo_7,
    			R.drawable.gallery_photo_8,
    			
    			R.drawable.gallery_photo_1,
    			R.drawable.gallery_photo_2,
    			R.drawable.gallery_photo_3,
    			R.drawable.gallery_photo_4,
    			R.drawable.gallery_photo_5,
    			R.drawable.gallery_photo_6,
    			R.drawable.gallery_photo_7,
    			R.drawable.gallery_photo_8
    	};
    	
		public int getCount() {
			return imgs.length;
		}

		public Object getItem(int item) {
			return item;
		}

		public long getItemId(int id) {
			return id;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			 ImageView imageView = new ImageView(context);
			 imageView.setImageResource(imgs[position]);
			 imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			 imageView.setLayoutParams(new Gallery.LayoutParams(136, 88));
	            
	         return imageView;
		}
    	
    }
}