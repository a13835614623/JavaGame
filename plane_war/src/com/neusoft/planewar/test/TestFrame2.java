package com.neusoft.planewar.test;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.core.MyFrame;
import com.neusoft.planewar.util.GameUtil;

/**
 * 
 * @author zzk<br>
 * 1.写一个类继承Frame类<br>
 * 2.设置窗口属性 <br>
 * 3.重写paint(Graphics g)
 */
public class TestFrame2 extends MyFrame {

	Image background = GameUtil.getImage("com/neusoft/planewar/img/timg.png");
	int x = 0;
	int y = 0;
	int xSpeed = 10;
	int ySpeed = 1;
	int width = background.getWidth(null);// 得到当前图片的宽度
	int height = background.getHeight(null);// 得到当前图片的宽度
	/**
	 * 添加Boolean类型的变量改变运动状态<br>
	 * 一开始向左运动
	 */
	boolean right = false;

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, x, y, null);
		if (right) {// 当前方向
			x += xSpeed;
		} else {
			x -= xSpeed;
		}

		if (x > (Constant.GAME_WIDTH - width)) {// 下一步的方向
			right = false;
		} else if (x < 0) {
			right = true;
		}
	}

	public static void main(String[] args) {
		TestFrame2 tf = new TestFrame2();
		tf.launchFrame();
	}
}
