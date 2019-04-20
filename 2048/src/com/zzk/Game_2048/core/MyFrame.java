package com.zzk.Game_2048.core;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.zzk.Game_2048.constant.Constant;

public class MyFrame extends Frame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 加载窗体
	 */
	public void loadFrame(){
		this.setTitle("2048");
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);//居中
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.setVisible(true);
		
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
		backg.setColor(Color.WHITE);
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
	class MyThread extends Thread{
		@Override
		public void run() {
			while(true){
				repaint();
				try {
					sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
