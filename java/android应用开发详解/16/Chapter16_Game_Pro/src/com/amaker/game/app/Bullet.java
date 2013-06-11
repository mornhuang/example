package com.amaker.game.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.amaker.game.Sprite;
/**
 * @author 郭宏志
 * 子弹类
 */
public class Bullet extends Sprite{
	// 帧宽和高
	private int frameWidth,frameHeight;
	// 屏幕宽和高
	private int screenWidth,screenHeight;
	// 是否存活
	private boolean isAlive;
	public Bullet(Bitmap Bitmap, int frameWidth, int frameHeight) {
		super(Bitmap, frameWidth, frameHeight);
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		defineReferencePixel(frameWidth/2, frameHeight/2);
	}
	// 设置屏幕尺寸
	public void setScreenSize(int screenWidth,int screenHeight){
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	// 激活玩家
	public void setAlive(boolean isAlive,int x,int y){
		this.isAlive = isAlive;
		setPosition(x,y);
	}
	
	// 激活玩家
	public void setAlive(boolean alive){
		this.isAlive = alive;
	}
	// 获得玩家存活状态
	public boolean getAlive(){
		return this.isAlive;
	}
	// 移动子弹
	public void move(){
		if(isAlive){
			move(0,-3);
		}
		if(getY()<0){
			isAlive = false;
		}
	}
	// 绘制子弹
	public void draw(Canvas c){
		if(isAlive)
		paint(c);
	}
	
}
