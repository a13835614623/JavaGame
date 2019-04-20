package com.zzk.teris.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.zzk.teris.constant.Constant;
import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.AbstractBlockFactory;
import com.zzk.teris.core.MyFrame;
import com.zzk.teris.core.Square;
import com.zzk.teris.core.factory.Block1Factory;
import com.zzk.teris.core.factory.Block2Factory;
import com.zzk.teris.core.factory.Block3Factory;
import com.zzk.teris.core.factory.Block4Factory;
import com.zzk.teris.core.factory.Block5Factory;
import com.zzk.teris.core.factory.Block6Factory;
import com.zzk.teris.core.factory.Block7Factory;
import com.zzk.teris.core.state.StopedState;
import com.zzk.teris.util.ImageUtil;

/**
 * 客户端
 * 
 * @author zzk
 */
@SuppressWarnings("serial")
public class TerisClient extends MyFrame {
	public static final AbstractBlockFactory[] blockFactory = { 
			new Block1Factory(), new Block2Factory(), new Block3Factory(),
			new Block4Factory(), new Block5Factory(), new Block6Factory(),
			new Block7Factory(), };
	public static final Image failImg = ImageUtil.images.get("fail");// 游戏结束图
	public static Square squares[][];
	public static AbstractBlock nextBlock;// 下一个块
	public static AbstractBlock block;// 当前块
	public static int score;// 分数
	public static final Random random=new Random();// 随机数产生对象
	public static boolean fail;// 游戏结束状态
	{
		//初始化游戏参数
		squares = new Square[Constant.GAME_HEIGHT / (Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE)
				+ 4][Constant.GAME_WIDTH / ((Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE)) + 2];
		nextBlock = newBlock();
		block = newBlock();
		fail = false;
		score = 0;
	}

	@Override
	public void loadFrame() {
		super.loadFrame();
		// 添加键盘监听器
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				block.keyPressed(e);
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		drawBack(g);// 绘制背景
		for (int i = 0; i < squares[20].length; i++) {
			if (squares[20][i] != null) {//方块到达上边界
				fail = true;
			}
		}
		if (!fail) {// 如果游戏未结束
			drawActiveBlock(g);// 绘制运动块
			drawStopSquare(g);// 会长静止基本块
			drawNextBlock(g);// 会长下一个块
		} else {
			// 绘制游戏结束图
			g.drawImage(failImg, (Constant.FRAME_WIDTH - failImg.getWidth(null)) / 2 - 120,
					(Constant.FRAME_HEIGHT - failImg.getHeight(null)) / 2, null);
		}
	}

	/**
	 * 绘制运动的方块
	 */
	private void drawActiveBlock(Graphics g) {
		if (block.getState() instanceof StopedState) {
			block = nextBlock;
			nextBlock = newBlock();
		} else {
			block.draw(g);
		}
	}

	/**
	 * 绘制静止的方块
	 * 
	 * @param g
	 */
	private void drawStopSquare(Graphics g) {
		for (int i = 1; i < squares.length; i++) {// 行号
			boolean isFull = true;
			for (int j = 1; j < squares[i].length; j++) {// 列号
				if (squares[i][j] != null) {
					squares[i][j].draw(g);
				} else {
					isFull = false;
				}
			}
			if (isFull) {// 如果一行满了，消除
				eliminate(i);
				System.out.println(1);
			}
		}
	}

	/**
	 * 消除指定行
	 */
	private void eliminate(int line) {
		score += 10;
		for (int k = line; k < squares.length - 1; k++) {
			for (int l = 1; l < squares[k].length; l++) {
				if (squares[k + 1][l] != null) {
					squares[k + 1][l].y += 42;
					squares[k][l] = squares[k + 1][l];
				} else {
					squares[k][l] = null;
				}
			}
		}
	}

	/**
	 * 产生新方块
	 * 
	 * @return
	 */
	private AbstractBlock newBlock() {
		int startX = Constant.GAME_X_LEFT + 42 * 7;// 块的初始横坐标
		int startY = Constant.GAME_Y_UP + 1;// 块初始纵坐标
		return blockFactory[random.nextInt(blockFactory.length)].getBlock(startX, startY,
				Constant.COLOR_BLOCKS[random.nextInt(Constant.COLOR_BLOCKS.length)]);
	}

	/**
	 * 绘制背景
	 * 
	 * @param g
	 */
	private void drawBack(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(Constant.GAME_X_LEFT, Constant.GAME_Y_UP, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		g.setColor(Color.RED);
		// 绘制红框
		for (int i = 1; i <= 6; i++) {
			g.drawRect(Constant.GAME_X_LEFT - i, Constant.GAME_Y_UP - i, Constant.GAME_WIDTH + 2 * i,
					Constant.GAME_HEIGHT + 2 * i);
			g.drawRect(Constant.GAME_X_RIGHT + i, Constant.GAME_Y_UP - i, 300, 300);
			g.drawRect(Constant.GAME_X_RIGHT + i, Constant.GAME_Y_UP + 300 - i, 300, 300);
		}
		// 绘制下一个方块
		drawNextBlock(g);
		g.setFont(new Font("微软雅黑", Font.BOLD, 40));
		// 绘制分数
		g.drawString("分  数 :" + score, Constant.GAME_X_RIGHT + 30, Constant.GAME_Y_UP + 350);
	}

	/**
	 * 绘制下一个方块
	 * 
	 * @param g
	 */
	private void drawNextBlock(Graphics g) {
		g.setFont(new Font("微软雅黑", Font.BOLD, 50));
		g.setColor(Color.BLACK);
		g.drawString("NEXT", Constant.GAME_X_RIGHT + 50, Constant.GAME_Y_UP + 50);
		for (Square square : nextBlock.getSquareList()) {
			g.setColor(nextBlock.getColor());
			g.fillRect(square.x + 400, square.y + 100, square.width, square.height);
		}
	}
	/**
	 * 获取Square对象
	 * @param square 小方块
	 * @param dRow 行偏移
	 * @param dCol 列偏移
	 * @return Square
	 */
	public static Square getSquare(Square square, int dRow, int dCol) {
		return TerisClient.squares[(Constant.GAME_Y_DOWN - square.y) / (Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE)
				+ 1 + dRow][(square.x - Constant.GAME_X_LEFT) / (Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE) + 1
						+ dCol];
	}
	public static void main(String[] args) {
		new TerisClient().loadFrame();
	}
}
