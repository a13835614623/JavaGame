package com.zzk.snake.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.zzk.snake.core.Food;
import com.zzk.snake.core.MyFrame;
import com.zzk.snake.core.MySnake;
import com.zzk.snake.util.ImageUtil;

public class SnakeClient extends MyFrame{
	
	MySnake mySnake = new MySnake(100, 100);//蛇
	Food food = new Food();//食物
	Image background = ImageUtil.images.get("background");//背景图片
	Image fail = ImageUtil.images.get("fail");//游戏结束的文字
	@Override
	public void loadFrame() {
		super.loadFrame();
		//添加键盘监听器，处理键盘按下事件
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				mySnake.keyPressed(e);//委托给mysnake
			}
		});
	}
	/**
	 * 绘制界面
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);//绘制背景
		if(mySnake.live){//如果蛇活着，就绘制
			mySnake.draw(g);
			if(food.live){//如果食物活着，就绘制
				food.draw(g);
				food.eaten(mySnake);//判断是否被吃
			}else{//否则，产生新食物
				food = new Food();
			}
		}else{//蛇死亡，弹出游戏结束字样
			g.drawImage(fail, (background.getWidth(null)-fail.getWidth(null))/2, (background.getHeight(null)-fail.getHeight(null))/2, null);
		}
		drawScore(g);//绘制分数
	}
	/**
	 * 绘制分数
	 * @param g
	 */
	public void drawScore(Graphics g){
		g.setFont(new Font("Courier New", Font.BOLD, 40));
		g.setColor(Color.WHITE);
		g.drawString("SCORE:"+mySnake.score,700,100);
	}
	public static void main(String[] args) {
		new SnakeClient().loadFrame();//加载窗体
	}
}
