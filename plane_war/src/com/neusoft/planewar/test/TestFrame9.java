package com.neusoft.planewar.test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.core.MyFrame;
import com.neusoft.planewar.util.GameUtil;

/**
 * 星形线
 * @author zzk
 *x=a*(2*cos(t)-cos(2*t))
 *y=a*(2*sin(t)-sin(2*t))
 */
public class TestFrame9 extends MyFrame {

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
	// double theta = Math.PI/6;
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, x, y, null);
		x=(int) (center.x+500*Math.pow(Math.cos(theta), 3));
		y=(int) (center.y+500*Math.pow(Math.sin(theta), 3));

		theta += speed;

	}

	public static void main(String[] args) {
		TestFrame9 tf = new TestFrame9();
		tf.launchFrame();
	}
}