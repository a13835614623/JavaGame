package com.zzk.snake.core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import com.zzk.snake.constant.Constant;
import com.zzk.snake.util.GameUtil;
import com.zzk.snake.util.ImageUtil;

public class MySnake extends SnakeObject implements Moveable {
	//蛇头图片（未旋转）
	private static final BufferedImage IMG_SNAKE_HEAD = (BufferedImage) ImageUtil.images.get("snake_head");

	private int speed;//移动速度
	private int length;//长度
	private int num;//
	public static List<Point> bodyPoints = new LinkedList<>();
	public int score = 0;//分数
	private static BufferedImage newImgSnakeHead;//旋转后的蛇头图片
	boolean up, down, left, right = true;//初始态向右
	public MySnake(int x, int y) {
		this.live = true;
		this.x = x;
		this.y = y;
		this.img = ImageUtil.images.get("snake_body");
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.speed = 5;
		this.length = 1;
		this.num = width / speed;
		newImgSnakeHead = IMG_SNAKE_HEAD;
	}

	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length=length;
	}
	/**
	 * 接收键盘按下事件
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (!down) {// 不能向初始方向的反方向移动
				up = true;
				down = false;
				left = false;
				right = false;
				newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);//旋转图片
			}
			break;
		case KeyEvent.VK_DOWN:
			if (!up) {
				up = false;
				down = true;
				left = false;
				right = false;
				newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);
			}
			break;
		case KeyEvent.VK_LEFT:
			if (!right) {
				up = false;
				down = false;
				left = true;
				right = false;
				newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (!left) {
				up = false;
				down = false;
				left = false;
				right = true;
				newImgSnakeHead = IMG_SNAKE_HEAD;
			}
			break;
		}
	}
	/**
	 * 移动
	 */
	@Override
	public void move() {
		if (up)
			y -= speed;
		else if (down)
			y += speed;
		else if (left)
			x -= speed;
		else if (right)
			x += speed;
	}
	/**
	 * 绘制
	 */
	@Override
	public void draw(Graphics g) {
		outOfBounds();//处理出界问题
		eatBody();//处理是否吃到身体问题
		bodyPoints.add(new Point(x, y));//保存轨迹
		if (bodyPoints.size() == (this.length+1) * num) {//当保存的轨迹点的个数为蛇的长度+1的num倍时
			bodyPoints.remove(0);//移除第一个
		}
		g.drawImage(newImgSnakeHead, x, y, null);//绘制蛇头
		drawBody(g);//绘制蛇身
		move();//移动
	}
	/**
	 * 处理是否吃到到身体问题
	 */
	public void eatBody(){
		for (Point point : bodyPoints) {
			for (Point point2 : bodyPoints) {
				if(point.equals(point2)&&point!=point2){
					this.live=false;//食物死亡
				}
			}
		}
	}
	/**
	 * 绘制蛇身
	 * @param g
	 */
	public void drawBody(Graphics g) {
		int length = bodyPoints.size() - 1-num;//前num个存储的是蛇头的当前轨迹坐标
		for (int i = length; i >= num; i -= num) {//从尾部添加
			Point p = bodyPoints.get(i);
			g.drawImage(img, p.x, p.y, null);
		}
	}

	/**
	 * 处理出界问题
	 */
	private void outOfBounds() {
		boolean xOut = (x <= 0 || x >= (Constant.GAME_WIDTH - width));
		boolean yOut = (y <= 40 || y >= (Constant.GAME_HEIGHT - height));
		if (xOut || yOut) {
			live = false;
		}
	}
}
