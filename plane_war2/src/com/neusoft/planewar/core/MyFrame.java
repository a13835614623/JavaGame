package com.neusoft.planewar.core;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.util.ImageUtil;

/**
 * 游戏中自定义窗口的父类 设置一次，终身使用
 * 
 * @author zzk
 *
 */
public class MyFrame extends Frame {
	/**
	 * 自定义生成窗口的方法
	 * 
	 * @throws InterruptedException
	 */
	public void launchFrame() {
		// 设置窗口大小
		setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		// 设置窗口位置
		// setLocation(0, 0);
		setLocationRelativeTo(null);// 相对居中，传入null，相对于显示器屏幕
		// 设置窗口标题
		setTitle("飞机大战(无尽版)");
		// 设置可见
		setVisible(true);
		// 设置不能改变大小
		setResizable(false);
		// 设置关闭窗口
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);// 设置关闭
			}
		});
		enableInputMethods(false);//屏蔽输入法
		setBackground(Color.BLACK);

		new MyThread().start();

	}

	/**
	 * 防止图片闪烁
	 * 
	 * @param g
	 */
	Image backImg = null;

	@Override
	public void update(Graphics g) {
		if (backImg == null) {
			backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		}
		Graphics backg = backImg.getGraphics();
		Color c = backg.getColor();
		backg.setColor(Color.BLACK);
		backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backg.setColor(c);
		paint(backg);		
		g.drawImage(backImg, 0, 0, null);
	}

	/**
	 * 这种 创建一个重新画的线程内部类
	 * 
	 * @param args
	 */
	class MyThread extends Thread {
		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
