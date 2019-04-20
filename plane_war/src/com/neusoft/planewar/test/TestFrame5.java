package com.neusoft.planewar.test;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.core.MyFrame;
import com.neusoft.planewar.util.GameUtil;

/**
 * 抛物线
 * @author zzk
 *
 */
public class TestFrame5 extends MyFrame {

	Image background = GameUtil.getImage("com/neusoft/planewar/img/ball.png");
	int x = 0;
	int y = 0;
	int width = background.getWidth(null);// 得到当前图片的宽度
	int height = background.getHeight(null);// 得到当前图片的宽度
	/**
	 * 任意角度theta
	 */
	double theta;
	int p = 5;

	double speed = 0.5;

	// double theta = Math.PI/6;
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, x, y, null);
		int t = (int) (Math.sin(theta + Math.PI / 2) / Math.cos(theta + Math.PI / 2));
		x = (Constant.GAME_WIDTH - width) / 2 + (int) (2 * p * t);
		y = 30 + (int) (t * t);

		theta += speed;

	}

	public static void main(String[] args) {
		TestFrame5 tf = new TestFrame5();
		tf.launchFrame();
	}
}
