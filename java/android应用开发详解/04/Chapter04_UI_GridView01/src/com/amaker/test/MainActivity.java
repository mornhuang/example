package com.amaker.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private GridView gv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gv = (GridView)findViewById(R.id.GridView01);
        gv.setNumColumns(4);
        // gv.setNumColumns(3);
        // String[] strs = {"a","a1","a2","b","b1","b2","c","c1","c2"};
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_gallery_item,strs);
        gv.setAdapter(new MyAdapter(this));
        
    }
    
    class MyAdapter extends BaseAdapter{
    	
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
    	Context context;
    	MyAdapter(Context context){
    		this.context = context;
    	}
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
			 ImageView imageView;
	            if (convertView == null) {
	                imageView = new ImageView(context);
	                imageView.setLayoutParams(new GridView.LayoutParams(45, 45));
	                imageView.setAdjustViewBounds(false);
	                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	                imageView.setPadding(8, 8, 8, 8);
	            } else {
	                imageView = (ImageView) convertView;
	            }
	            imageView.setImageResource(imgs[position]);
	            return imageView;
		}
    }
}