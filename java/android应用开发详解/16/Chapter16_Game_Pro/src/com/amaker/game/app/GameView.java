package com.amaker.game.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.View;

/**
 * 
 * @author 郭宏志
 * 游戏视图类
 */
public class GameView extends View implements Runnable{
	// 玩家Bitmap
	private Bitmap playerBitmap;
	// 子弹Bitmap
	private Bitmap bulletBitmap;
	// 敌人Bitmap
	private Bitmap enemyBitmap;
	// 玩家
	private Player player;
	// 子弹
	private Bullet[] bullets = new Bullet[3];
	// 敌人
	private Enemy enemy;
	// Context
	private Context context;
	// 屏幕宽、高
	public int screenWidth,screenHeight;
	// 爆炸图片
	public Bitmap exploreBitmap;
	// 判断是否爆炸
	private boolean isExplore;
	
	// 构造方法
	public GameView(Context context,int screenWidth,int screenHeight) {
		super(context);
		this.context = context;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		setFocusable(true);
	}
	// 绘制方法
	@Override
	public void draw(Canvas canvas) {
		// 画玩家
		player.paint(canvas);
		// 画子弹
		for (int i = 0; i <bullets.length; i++) {
			bullets[i].draw(canvas);
		}
		// 绘制敌人
		enemy.draw(canvas);
		
		if(isExplore){
			canvas.drawBitmap(exploreBitmap, enemy.getY(), enemy.getX(), null);
			isExplore = false;
			enemy.setAlive(false);
		}
		
		super.draw(canvas);
	}
	
	// 键盘事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			player.move(Player.UP);
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			player.move(Player.DOWN);
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			player.move(Player.LEFT);
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			player.move(Player.RIGHT);
			break;
		case KeyEvent.KEYCODE_DPAD_CENTER:
			for (int i = 0; i < bullets.length; i++) {
				if(bullets[i].getAlive()==false){
					bullets[i].setAlive(true, 
							player.getRefPixelX()-bullets[i].getWidth()/2,
							player.getRefPixelY()-player.getHeight());
					break;
				}
			}
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 线程方法实现
	public void run() {
		load();
		init();
		while(true){
			if(!enemy.isAlive()){
				enemy.setAlive();
			}
			tick();
			postInvalidate();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
	
	// 加载资源
	public void load(){
		// 获得玩家图片
		playerBitmap = GameHelper.getBitmap(context,R.drawable.player);
		// 实例化玩家
		player = new Player(playerBitmap,playerBitmap.getWidth(),playerBitmap.getHeight());
		// 设置屏幕宽和高
		player.setScreenSize(screenWidth, screenHeight);
		// 获得子弹图片
		bulletBitmap = GameHelper.getBitmap(context,R.drawable.bullet);
		
		exploreBitmap = GameHelper.getBitmap(context,R.drawable.explored);
		for(int i=0;i<bullets.length;i++){
			// 实例化子弹
			bullets[i] = new Bullet(bulletBitmap,bulletBitmap.getWidth(),bulletBitmap.getHeight());
			// 设置屏幕宽和高
			bullets[i].setScreenSize(screenWidth, screenHeight);
		}
		// 获得敌人图片
		enemyBitmap = GameHelper.getBitmap(context, R.drawable.e6);
		// 实例化敌人
		enemy = new Enemy(enemyBitmap,enemyBitmap.getWidth(),enemyBitmap.getHeight());
		// 设置屏幕宽和高
		enemy.setScreenSize(screenWidth, screenHeight);
	}
	
	// 初始化
	public void init(){
		// 初始化玩家
		player.init();
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].setAlive(false);
		}
	}
	
	// 启动线程
	public void start(){
		new Thread(this).start();
	}
	
	public void tick(){
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].move();
			if(bullets[i].collidesWith(enemy, false)){
				isExplore = true;
			}
		}
		enemy.move();
		
	}
}
