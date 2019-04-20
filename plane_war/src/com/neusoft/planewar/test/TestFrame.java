package com.neusoft.planewar.test;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.core.MyFrame;
import com.neusoft.planewar.util.GameUtil;
import com.neusoft.planewar.util.ImageUtil;

/**
 * 
 * @author zzk 1.写一个类继承Frame类 2.设置窗口属性 3.重写paint(Graphics g)
 */
public class TestFrame extends MyFrame {


	Image background = GameUtil.getImage("com/neusoft/planewar/img/background.png");
	int x = 0;
	int y = 0;
	int xSpeed = 1;
	int ySpeed = 1;

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, x += xSpeed, y += ySpeed, null);
	}

	public static void main(String[] args) {
		TestFrame tf = new TestFrame();
		tf.launchFrame();
	}
}
