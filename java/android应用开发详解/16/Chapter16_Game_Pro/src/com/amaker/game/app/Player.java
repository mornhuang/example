package com.amaker.game.app;

import android.graphics.Bitmap;
import com.amaker.game.Sprite;

/**
 * 
 * @author 郭宏志
 * 玩家实现类
 */
public class Player extends Sprite{
	// 定义屏幕宽和高
	private int screenWidth,screenHeight;
	// 定义玩家宽和高
	private int frameWidth,frameHeight;
	// 定义玩家是否活着
	private boolean isAlive = false;
	// 定义移动方向常量，上、下、左、右
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	// 定义移动速度
	public static int SPEED = 4;
	
	// 构造方法
	public Player(Bitmap Bitmap,int frameWidth,int frameHeight) {
		super(Bitmap);
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		defineReferencePixel(frameWidth/2, frameHeight/2);
	}
	// 设置屏幕尺寸
	public void setScreenSize(int screenWidth,int screenHeight){
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	// 激活玩家
	public void setAlive(boolean isAlive){
		this.isAlive = isAlive;
		setPosition(screenWidth/2, screenHeight/2);
	}
	// 获得玩家存活状态
	public boolean getAlive(){
		return this.isAlive;
	}
	// 移动玩家
	public void move(int direction){
		// 向上移动
		if(direction==UP){
			move(0, -SPEED);
			if(getY()<0){
				setPosition(getX(), 0);
			}
		}
		// 向下移动
		if(direction==DOWN){
			move(0, SPEED);
			if(getY()>screenHeight-frameHeight){
				setPosition(getX(), screenHeight-frameHeight);
			}
		}
		
		// 向左移动
		if(direction==LEFT){
			move(-SPEED, 0);
			if(getX()<0){
				setPosition(0, getY());
			}
		}
		// 向右移动
		if(direction==RIGHT){
			move(SPEED, 0);
			if(getX()>screenWidth-frameWidth){
				setPosition(screenWidth-frameWidth,getY());
			}
		}
	}
	// 初始化玩家
	public void init(){
		SPEED = 4;
		setAlive(true);
	}
}
