package com.neusoft.planewar.test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.core.MyFrame;
import com.neusoft.planewar.util.GameUtil;

/**
 * 心形线
 * @author zzk
 *x=a*(2*cos(t)-cos(2*t))
 *y=a*(2*sin(t)-sin(2*t))
 */
public class TestFrame8 extends MyFrame {

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
	int a=150;
	// double theta = Math.PI/6;
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, x, y, null);
		x=(int) (center.x+a*(2*Math.cos(theta+Math.PI/2)+Math.cos(2*theta+Math.PI/2)));
		y=(int) (center.y+a*(2*Math.sin(theta+Math.PI/2)+Math.sin(2*theta+Math.PI/2)));

		theta += speed;

	}

	public static void main(String[] args) {
		TestFrame8 tf = new TestFrame8();
		tf.launchFrame();
	}
}