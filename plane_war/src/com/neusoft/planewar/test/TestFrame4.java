package com.neusoft.planewar.test;

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
public class TestFrame4 extends MyFrame {

	Image background = GameUtil.getImage("com/neusoft/planewar/img/ball.png");
	int x = 0;
	int y = 0;
	int width = background.getWidth(null);// 得到当前图片的宽度
	int height = background.getHeight(null);// 得到当前图片的宽度
	/**
	 * 任意角度theta
	 */
	double theta;
	/**
	 * 半长轴
	 */
	int longAixs = (Constant.GAME_WIDTH-width)/2;
	/**
	 * 半短轴
	 */
	int shortAixs = (Constant.GAME_HEIGHT-height)/2;
	double speed = 0.05;

	// double theta = Math.PI/6;
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, x, y, null);
		x = longAixs+(int) (longAixs * Math.cos(theta));
		y = shortAixs+(int) (shortAixs * Math.sin(theta));
		theta += speed;
		
	}

	public static void main(String[] args) {
		TestFrame4 tf = new TestFrame4();
		tf.launchFrame();
	}
}
