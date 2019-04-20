package com.zzk.teris.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.zzk.teris.constant.Constant;
import com.zzk.teris.core.Block;
import com.zzk.teris.core.MyFrame;
import com.zzk.teris.core.Square;
import com.zzk.teris.util.ImageUtil;

/**
 * 客户端
 * 
 * @author zzk
 */
public class TerisClient extends MyFrame {
	private static final long serialVersionUID = 8099351486626087795L;
	public Block block = null;
	public static Square squares[][] = new Square[Constant.GAME_HEIGHT / (Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE)+4]
			[Constant.GAME_WIDTH / ((Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE))+2];
	public Block nextBlock=null;
	public TerisClient() {
		super();
		block = newBlock();
		nextBlock=newBlock();
	}

	@Override
	public void loadFrame() {
		super.loadFrame();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				block.keyPressed(e);
			}
		});
	}
	public static boolean fail = false;
	public static Image failImg = ImageUtil.images.get("fail");
	@Override
	public void paint(Graphics g) {
		drawBack(g);
		for (int i = 0; i < squares[20].length; i++) {
			if(squares[20][i]!=null){
				fail=true;
			}
		}
		if(!fail){
			drawActiveBlock(g);
			drawStopSquare(g);
			drawNextBlock(g);
		}else{
			g.drawImage(failImg, (Constant.FRAME_WIDTH-failImg.getWidth(null))/2-120, (Constant.FRAME_HEIGHT-failImg.getHeight(null))/2, null);
		}
	}
	/**
	 * 绘制运动的方块
	 */
	private void drawActiveBlock(Graphics g) {
		if(block.getState()==Block.STATE_STOPED){
			block=nextBlock;
			nextBlock=newBlock();
		}else{
			block.draw(g);
		}
	}
	/**
	 *绘制静止的方块
	 * @param g
	 */
	private void drawStopSquare(Graphics g) {
		for (int i = 1; i < squares.length; i++) {//行号
			boolean isFull=true;
			for (int j = 1; j < squares[i].length; j++) {//列号
				if (squares[i][j] != null) {
					squares[i][j].draw(g);
					g.setColor(Color.BLACK);
					g.drawRect(squares[i][j].getRect().x, squares[i][j].getRect().y, squares[i][j].getRect().width,  squares[i][j].getRect().height);
				}else{
					isFull=false;
				}
			}
			if(isFull){//如果一行满了，消除
				eliminate(i);
				System.out.println(1);
			}
		}
	}
	/**
	 * 消除指定行
	 */
	private void eliminate(int line){
		score+=10;
		for(int k=line;k<squares.length-1;k++){
			for(int l=1;l<squares[k].length;l++){
				if(squares[k+1][l]!=null){
					squares[k+1][l].getRect().y+=42;
					squares[k][l]=squares[k+1][l];
				}else{
					squares[k][l]=null;
				}
			}
		}
	}
	private Random random = new Random();

	private Block newBlock() {
		return new Block(Constant.GAME_X_LEFT + 42 * 7, Constant.GAME_Y_UP+1, random.nextInt(7) + 1);
	}

	private void drawBack(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(Constant.GAME_X_LEFT, Constant.GAME_Y_UP, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		g.setColor(Color.RED);
		//绘制红框
		for (int i = 1; i <= 6; i++) {
			g.drawRect(Constant.GAME_X_LEFT - i, Constant.GAME_Y_UP - i, Constant.GAME_WIDTH + 2 * i,
					Constant.GAME_HEIGHT + 2 * i);
			g.drawRect(Constant.GAME_X_RIGHT+i, Constant.GAME_Y_UP - i, 300, 300);
			g.drawRect(Constant.GAME_X_RIGHT+i, Constant.GAME_Y_UP +300- i, 300, 300);
		}
		//绘制下一个方块
		drawNextBlock(g);
		g.setFont(new Font("微软雅黑", Font.BOLD, 40));
		//绘制分数
		g.drawString("分  数 :"+score, Constant.GAME_X_RIGHT+30, Constant.GAME_Y_UP+350);
	}
	public static int score=0;
	/**
	 * 绘制下一个方块
	 * @param g
	 */
	private void drawNextBlock(Graphics g) {
		g.setFont(new Font("微软雅黑", Font.BOLD, 50));
		g.setColor(Color.BLACK);
		g.drawString("NEXT", Constant.GAME_X_RIGHT+50, Constant.GAME_Y_UP+50);
		for (Rectangle rect : nextBlock.squareList) {
			g.setColor(nextBlock.getColor());
			g.fillRect(rect.x+400, rect.y+100, rect.width, rect.height);
		}
	}

	public static void main(String[] args) {
		new TerisClient().loadFrame();
	}
}
