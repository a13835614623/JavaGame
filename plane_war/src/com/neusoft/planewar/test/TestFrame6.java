package com.neusoft.planewar.test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.core.MyFrame;
import com.neusoft.planewar.util.GameUtil;

/**
 * 阿基米德螺旋线
 * @author zzk
 *
 */
public class TestFrame6 extends MyFrame {

	Image background = GameUtil.getImage("com/neusoft/planewar/img/snow.png");
	int x = 0;
	int y = 0;
	int width = background.getWidth(null);// 得到当前图片的宽度
	int height = background.getHeight(null);// 得到当前图片的宽度
	/**
	 * 任意角度theta
	 */
	Point center = new Point((Constant.GAME_WIDTH-width)/2, (Constant.GAME_HEIGHT-height)/2);
	double theta;
	double speed = 0.5;
	int a=1;
	int b=1;
	// double theta = Math.PI/6;
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, x, y, null);
		x = (int) (center.x+(a+b*theta)*(Math.cos(theta)));
		y = (int) (center.y+(a+b*theta)*(Math.sin(theta)));

		theta += speed;

	}

	public static void main(String[] args) {
		TestFrame6 tf = new TestFrame6();
		tf.launchFrame();
	}
}