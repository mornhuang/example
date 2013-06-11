package com.amaker.game.app;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.amaker.game.Sprite;
/**
 * @author 郭宏志
 * 敌人图层类
 */
public class Enemy extends Sprite {
	// 定义敌人宽和高
	private int frameWidth,frameHeight;
	// 定义屏幕宽和高
	private int screenWidth,screenHeight;
	// 判断敌人是否存活
	private boolean isAlive;
	// 随机数
	Random r;
	public Enemy(Bitmap Bitmap, int frameWidth, int frameHeight) {
		super(Bitmap, frameWidth, frameHeight);
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		defineReferencePixel(frameWidth / 2, frameHeight / 2);
		r = new Random();
	}
	// 设置屏幕的宽和高
	public void setScreenSize(int screenWidth,int screenHeight){
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	
	// 设置敌人存活
	public void setAlive(){
		isAlive = true;
		int x = (r.nextInt() & 0x7fffffff)%(screenWidth-frameWidth);
		setPosition(x,0);
	}
	
	public void setAlive(boolean alive){
		this.isAlive = alive;
	}
	// 移动
	public void move(){
		if(isAlive){
			move(0, 3);
			if(getY()>screenHeight){
				isAlive=false;
			}
		}
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	// 绘制敌人
	public void draw(Canvas canvas){
		if(isAlive){
			paint(canvas);
		}
	}

}
