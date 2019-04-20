package com.zzk.Game_2048.core;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.zzk.Game_2048.client.Client;
import com.zzk.Game_2048.constant.Constant;


public class BlockLoader implements Drawable {

	public static Random random = new Random();
	public Block[][] blocks = new Block[6][6];
	public List<Block> blockList = new CopyOnWriteArrayList<Block>();

	public BlockLoader() {
		super();
		init();
	}

	public void init() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (i == 5 || j == 5 || i == 0 || j == 0) {
					blocks[i][j] = new Block(i, j, 0, this);
				}
			}
		}
		 int x1 = randomBlockPoint().x;// 1-4
		 int y1 = randomBlockPoint().y;
		 blocks[x1][y1] = new Block(x1, y1, 1,this);
		 blockList.add(blocks[x1][y1]);
		 int x2 = randomBlockPoint().x;
		 int y2 = randomBlockPoint().y;
		 while (x1 == x2 && y1 == y2) {
		 x2 = randomBlockPoint().x;
		 y2 = randomBlockPoint().y;
		 }
		 blocks[x2][y2] = new Block(x2, y2, 1,this);
		 blockList.add(blocks[x2][y2]);
		 score=0;
	}

	public static Point randomBlockPoint() {
		return new Point(random.nextInt(4) + 1, random.nextInt(4) + 1);
	}

	public void newBlock() {
		int x = randomBlockPoint().x;// 1-4
		int y = randomBlockPoint().y;
		int i = 0;
		while (blocks[x][y] != null && i < 100) {
			x = randomBlockPoint().x;
			y = randomBlockPoint().y;
			i++;
		}
		if (blocks[x][y] == null) {
			newBlock = blocks[x][y] = new Block(x, y, 1, this);
			blockList.add(newBlock);
			System.out.println("newBlock:" + newBlock);
		} else {
			Client.gameStart = false;// ÓÎÏ·½áÊø
		}
	}

	public static boolean isPressed = false;
	public static Block newBlock = null;

	public void keyPressed(KeyEvent e) {
		if (!isPressed) {
			int direction = e.getKeyCode();
			switch (direction) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				direction = Block.DIRECTION_UP;
				isPressed = true;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				direction = Block.DIRECTION_DOWN;
				isPressed = true;
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				direction = Block.DIRECTION_LEFT;
				isPressed = true;
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				direction = Block.DIRECTION_RIGHT;
				isPressed = true;
				break;
			default:
				direction = -1;
				break;
			}
			if (isPressed) {
				for (Block block : blockList) {
					block.keyPressed(direction);
				}
			}
		}
		// newBlock();
	}

	public static int score = 0;
	public static int bestScore = 0;
	@Override
	public void draw(Graphics g) throws CloneNotSupportedException {
		boolean newFlag = true;
		for (Block block : blockList) {
			if (block.live) {
				block.draw(g);
				g.setFont(new Font("Î¢ÈíÒ¹ºÚ", Font.BOLD, 20));
				if (block.state != Block.STATE_UNKOWN) {
					newFlag = false;
				}
			} else {
				blockList.remove(block);
			}
		}
		if (newFlag && isPressed) {
			newBlock();
			isPressed = false;
		}
//		 drawTestInfo(g);//²âÊÔÐÅÏ¢
	}

	public void drawTestInfo(Graphics g) {
		// ²âÊÔ
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				int x = Constant.BACK_Y + Constant.BLOCK_SPACE * i + Constant.BLOCK_WIDTH * (i - 1);
				int y = Constant.BACK_X + Constant.BLOCK_SPACE * j + Constant.BLOCK_WIDTH * (j - 1);
				if (blocks[i][j] == null) {
					g.drawString("null", x, y);
				} else {
					g.drawString("(" + x + "," + y + ")" + blocks[i][j].state, x, y);
				}
			}
		}
	}
}
