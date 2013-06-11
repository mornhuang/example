package com.amaker.game.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameHelper {
	public static  Bitmap getBitmap(Context context ,int id){
		return BitmapFactory.decodeResource(context.getResources(), id);
	}
}
