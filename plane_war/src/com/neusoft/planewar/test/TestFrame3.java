package com.neusoft.planewar.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.core.MyFrame;
import com.neusoft.planewar.util.GameUtil;

/**
 * 
 * @author zzk<br>
 *         1.写一个类继承Frame类<br>
 *         2.设置窗口属性 <br>
 *         3.重写paint(Graphics g)
 */
public class TestFrame3 extends MyFrame {

	Image background = GameUtil.getImage("com/neusoft/planewar/img/ball.png");
	int x = 0;
	int y = 0;
	double speed = 10;
	int width = background.getWidth(null);// 得到当前图片的宽度
	int height = background.getHeight(null);// 得到当前图片的宽度
	/**
	 * 任意角度theta
	 */
	double theta = Math.toRadians(60);

	// double theta = Math.PI/6;
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, x, y, null);
		x += speed * Math.cos(theta);
		y += speed * Math.sin(theta);

		if (speed <= 0) {
			speed = 0;
		} else {
			speed -= 0.001;
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("微软雅黑", Font.BOLD, 20));
		g.drawString("speed:" + speed, 200, 100);
		if (y > (Constant.GAME_HEIGHT - height) || y < 0) {
			theta = -theta;
		}
		if (x > (Constant.GAME_WIDTH - width) || x < 0) {
			theta = Math.PI - theta;
		}
	}

	public static void main(String[] args) {
		TestFrame3 tf = new TestFrame3();
		tf.launchFrame();
	}
}
