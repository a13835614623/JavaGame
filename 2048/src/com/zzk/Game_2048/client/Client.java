package com.zzk.Game_2048.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.zzk.Game_2048.constant.Constant;
import com.zzk.Game_2048.core.BlockLoader;
import com.zzk.Game_2048.core.MyFrame;

public class Client extends MyFrame{
	
	public BlockLoader loader = new BlockLoader();
	@Override
	public void loadFrame() {
		super.loadFrame();
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				loader.keyPressed(e);
			}
		});
	}
	@Override
	public void paint(Graphics g) {
		drawBasic(g);
		drawScore(g);
		if(gameStart){
			try {
				loader.draw(g);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}else{
			if(!gameStart&flag)
				gameReset();
		}
	}
	public static boolean gameStart =true;
	public static boolean flag=true;
	public void gameReset(){
		flag=false;
		int m = JOptionPane.showOptionDialog(null, "对不起 , 游戏结束 ! 点击确定重新开始","游戏结束",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,new String[]{"确定","关闭游戏"},"确定");
		if(m==JOptionPane.YES_OPTION){
			gameStart=true;
			flag=true;
			loader=new BlockLoader();
		}else{
			System.exit(0);
		}
	}
	/**
	 * 画分数
	 */
	private void drawScore(Graphics g) {
		g.setColor(Color.RED);
		g.fillRoundRect(Constant.BACK_X, Constant.BACK_Y-100, 220, 80, 10, 10);
		g.setColor(Color.WHITE);
		g.setFont(new Font("幼圆", Font.BOLD, 30));
		g.drawString("分数:"+BlockLoader.score+"",Constant.BACK_X, Constant.BACK_Y-50);
		
		g.setColor(Color.GREEN);
		g.fillRoundRect(Constant.BACK_RIGHT_IN_X+Constant.BLOCK_SPACE-250, Constant.BACK_Y-100, 250, 80, 10, 10);
		g.setColor(Color.WHITE);
		g.setFont(new Font("幼圆", Font.BOLD, 30));
		g.drawString("最高分:"+BlockLoader.bestScore+"",Constant.BACK_RIGHT_IN_X+Constant.BLOCK_SPACE-250, Constant.BACK_Y-50);
		
	}
	/**
	 * 画背景
	 * @param g
	 */
	private void drawBasic(Graphics g) {
		g.setColor(Constant.COLOR_BACK);
		g.fillRoundRect(Constant.BACK_X, Constant.BACK_Y, Constant.BACK_WIDTH, Constant.BACK_HEIGHT, 20, 20);
		g.setColor(Constant.COLOR_BLOCK_BACK);
		for(int i=1;i<=4;i++){
			for(int j=1;j<=4;j++){
				int xStart =Constant.BACK_X+Constant.BLOCK_SPACE*j+Constant.BLOCK_WIDTH*(j-1);
				int yStart = Constant.BACK_Y+Constant.BLOCK_SPACE*i+Constant.BLOCK_WIDTH*(i-1);
				g.fillRoundRect(xStart, yStart, 
						Constant.BLOCK_WIDTH, Constant.BLOCK_WIDTH, 10, 10);
			}
		}
	}
	public static void main(String[] args) {
		new Client().loadFrame();
	}
}
