package com.zzk.snake.core;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.zzk.snake.constant.Constant;

public class MyFrame extends Frame{
	/**
	 * 加载窗体
	 */
	public void loadFrame(){
		this.setTitle("贪吃蛇");//设置窗体标题
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);//设置窗体大小
		this.setBackground(Color.BLACK);//设置背景
		this.setLocationRelativeTo(null);//居中
		//设置可关闭
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//设置可见
		this.setVisible(true);
		//运行重绘线程
		new MyThread().start();
	}
	/**
	 * 防止图片闪烁，使用双重缓存
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
	 * 这里创建一个不断重绘的线程内部类
	 * 
	 * @param args
	 */
	class MyThread extends Thread{
		@Override
		public void run() {
			while(true){
				repaint();
				try {
					sleep(30);//每30毫秒重绘一次
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
