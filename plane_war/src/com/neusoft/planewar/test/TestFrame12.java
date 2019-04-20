package com.neusoft.planewar.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.core.MyFrame;
import com.neusoft.planewar.util.GameUtil;

/**
 * 蝴蝶曲线
 * @author zzk
 *x=a*(2*cos(t)-cos(2*t))
 *y=a*(2*sin(t)-sin(2*t))
 */
public class TestFrame12 extends MyFrame {

	Image background = GameUtil.getImage("com/neusoft/planewar/img/ball.png");
	int x = 0;
	int y = 0;
	int width = background.getWidth(null);// 得到当前图片的宽度
	int height = background.getHeight(null);// 得到当前图片的宽度
	/**
	 * 任意角度theta
	 */
	Point center = new Point((Constant.GAME_WIDTH-width)/2, (Constant.GAME_HEIGHT-height)/2);
	double theta;
	double speed = 0.05;
	int a=30;
	// double theta = Math.PI/6;
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, x, y, null);
		x =  (int) (Constant.GAME_WIDTH/2+a*Math.sin(theta)*(Math.pow(Math.E,Math.cos(theta)-2*Math.cos(4*theta)-Math.pow(Math.sin(theta/12), 5))));
		y =  (int) (Constant.GAME_HEIGHT/2+a*Math.cos(theta)*(Math.pow(Math.E,Math.cos(theta)-2*Math.cos(4*theta)-Math.pow(Math.sin(theta/12), 5))));
		theta=theta+speed;
		g.setFont(new Font("微软雅黑", Font.BOLD, 20));
		g.setColor(Color.WHITE);
		g.drawString("speed:"+speed, center.x,center.y);
		
	}

	public static void main(String[] args) throws InterruptedException {
		TestFrame12 tf = new TestFrame12();
		tf.launchFrame();
	}
}