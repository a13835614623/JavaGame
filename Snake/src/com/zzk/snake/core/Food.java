package com.zzk.snake.core;

import java.awt.Graphics;
import java.awt.Point;

import com.zzk.snake.constant.Constant;
import com.zzk.snake.util.ImageUtil;

public class Food extends SnakeObject{

	public Food(){
		this.live=true;
		this.img=ImageUtil.images.get("food");
		this.width=img.getWidth(null);
		this.height=img.getHeight(null);
		this.x=(int) (Math.random()*(Constant.GAME_WIDTH-width+10));
		this.y=(int) (Math.random()*(Constant.GAME_HEIGHT-40-height)+40);

	}
	/**
	 * 食物被吃的方法
	 * @param mySnake
	 */
	public void eaten(MySnake mySnake){
		if(mySnake.getRectangle().intersects(this.getRectangle())&&live&&mySnake.live){
			this.live=false;//食物死亡
			mySnake.setLength(mySnake.getLength()+1);//长度加一
			mySnake.score+=10*mySnake.getLength();//加分
		}
	}
	/**
	 * 绘制食物
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
}
