package com.zzk.teris.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import com.zzk.teris.client.TerisClient;
import com.zzk.teris.constant.Constant;

public class Block implements Drawable, Moveable {

	private int x, y;
	private int type;
	public List<Rectangle> squareList = new LinkedList<>();

	public Block(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		initSquareList();
		state = STATE_ACTIVE;
		this.color = Constant.COLOR_BLOCKS[(int) (Math.random() * 9)];
		MyFrame.sleepTime = 30;
		updateCenterPoint();
	}

	private boolean rotate;// 旋转方块
	private boolean left;// 旋转方块
	private boolean right;// 旋转方块
	private boolean down;// 旋转方块
	private int downSpeed = 1;
	private Point center = new Point(0, 0);

	private int state;
	public static final int STATE_ACTIVE = 1;// 活跃运动态
	public static final int STATE_STOP = 2;// 静止态
	public static final int STATE_STOPED = 3;// 完全静止态

	private Color color;

	public Color getColor() {
		return color;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	int count = 1;

	/**
	 * 旋转方块
	 */
	private void rotate() {
		if (x <= Constant.GAME_X_LEFT) {
			for (Rectangle rect : squareList) {
				int dx = rect.x - center.x;
				int dy = rect.y - center.y;
				rect.setLocation(center.x - dy + Constant.BLOCK_SPACE
						+ count * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE) / 2, center.y + dx);
			}
		} else if (maxX >= Constant.GAME_X_RIGHT - Constant.BLOCK_WIDTH / 2) {
			for (Rectangle rect : squareList) {
				int dx = rect.x - center.x;
				int dy = rect.y - center.y;
				rect.setLocation(center.x - dy - Constant.BLOCK_WIDTH * 2 - Constant.BLOCK_SPACE
						+ count * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE) / 2, center.y + dx);
			}
		} else {
			for (Rectangle rect : squareList) {
				int dx = rect.x - center.x;
				int dy = rect.y - center.y;
				rect.setLocation(center.x - dy - Constant.BLOCK_WIDTH
						+ count * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE) / 2, center.y + dx);
			}
		}
	}

	@Override
	public void move() {
		if (left || right)
			for (Rectangle rect : squareList) {
				if (rect.getCenterX() == minX&&left) {
					if (getSquare(rect, 0, -1) != null || getSquare(rect, 1, -1) != null
							|| getSquare(rect, -1, -1) != null) {
						left = false;
					}
				}
				if (rect.getCenterX() == maxX&&right) {
					if (getSquare(rect, 0, 1) != null || getSquare(rect, 1, 1) != null
							|| getSquare(rect, -1, 1) != null) {
						right = false;
					}
				}
			}
		if (rotate && type != 4) {
			rotate();
			count = -count;
			rotate = false;
		} else if (left) {
			x -= (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE);
			for (Rectangle rect : squareList) {
				rect.x -= (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE);
			}
			left = false;
		} else if (right) {
			x += (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE);
			for (Rectangle rect : squareList) {
				rect.x += (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE);
			}
			right = false;
		} else if (down) {
			MyFrame.sleepTime /= 30;
			down = false;
		}
		y += downSpeed;
		for (Rectangle rect : squareList) {
			rect.y += downSpeed;
		}
	}

	@Override
	public void draw(Graphics g) {
		updateCenterPoint();// 更新中心点
		saveSquares();// 保存square
		switch (state) {
		case STATE_ACTIVE:
			outOfBounds();// 处理越界问题
			move();// 移动
			drawContent(g);// 绘制
			break;
		case STATE_STOP:
			saveSquares();// 保存square
			this.state = STATE_STOPED;
		}
	}

	private void drawContent(Graphics g) {
		g.setColor(color);
		for (Rectangle rect : squareList) {
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
	}

	private Square getSquare(Rectangle rect, int dRow, int dCol) {
		return TerisClient.squares[(Constant.GAME_Y_DOWN - rect.y) / (Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE) + 1
				+ dRow][(rect.x - Constant.GAME_X_LEFT) / (Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE) + 1 + dCol];
	}

	public void saveSquares() {
		switch (state) {
		case STATE_ACTIVE:
			for (Rectangle rect : squareList) {
				Square squareNext = getSquare(rect, -1, 0);
				if (squareNext != null) {
					this.stop();
				}
			}
			break;
		case STATE_STOP:
			for (Rectangle rect : this.squareList) {
				if (getSquare(rect, 0, 0) == null) {
					TerisClient.squares[(Constant.GAME_Y_DOWN - rect.y) / (Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE)
							+ 1][(rect.x - Constant.GAME_X_LEFT) / (Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE)
									+ 1] = new Square((Rectangle) rect.clone(), color);
				}
			}
			break;
		}

	}

	public Point getCenter() {
		return center;
	}

	/**
	 * 处理边界
	 */
	public void outOfBounds() {
		if (maxY == (Constant.GAME_Y_DOWN - Constant.BLOCK_HEIGHT / 2 - Constant.BLOCK_SPACE)) {
			stop();
		}
		if (x <= Constant.GAME_X_LEFT) {
			left = false;
		}
		if (maxX >= Constant.GAME_X_RIGHT - Constant.BLOCK_WIDTH / 2) {
			right = false;
		}
	}

	public void stop() {
		state = STATE_STOP;
		down = right = left = rotate = false;
	}

	int minX;
	int minY;
	int maxX;
	int maxY;

	/**
	 * 更新中心点
	 */
	public void updateCenterPoint() {
		minX = maxX = (int) squareList.get(0).getCenterX();
		minY = maxY = (int) squareList.get(0).getCenterY();
		for (Rectangle rect : squareList) {
			if (rect.getCenterX() < minX) {
				minX = (int) rect.getCenterX();
			} else if (rect.getCenterX() > maxX) {
				maxX = (int) rect.getCenterX();
			}
			if (rect.getCenterY() < minY) {
				minY = (int) rect.getCenterY();
			} else if (rect.getCenterY() > maxY) {
				maxY = (int) rect.getCenterY();
			}
		}
		center.x = (minX + maxX) / 2;
		center.y = (minY + maxY) / 2;
		x = minX - Constant.BLOCK_WIDTH / 2;
		y = minY - Constant.BLOCK_HEIGHT / 2;
	}

	/**
	 * 根据type填充squareList
	 */
	private void initSquareList() {
		switch (type) {
		case 1:
			for (int i = 0; i < 4; i++) {
				squareList.add(new Rectangle(x + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), y,
						Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT));
			}
			break;
		case 2:
			squareList.add(new Rectangle(x, y, Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT));
			for (int i = 0; i < 3; i++) {
				squareList.add(new Rectangle(x + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE),
						y + Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE, Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT));
			}
			break;
		case 3:
			squareList.add(new Rectangle(x + 2 * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), y, Constant.BLOCK_WIDTH,
					Constant.BLOCK_HEIGHT));
			for (int i = 0; i < 3; i++) {
				squareList.add(new Rectangle(x + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE),
						y + Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE, Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT));
			}
			break;
		case 4:
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					squareList.add(new Rectangle(x + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE),
							y + j * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), Constant.BLOCK_WIDTH,
							Constant.BLOCK_HEIGHT));
				}
			}
			break;
		case 5:
			for (int i = 1; i <= 2; i++) {
				squareList.add(new Rectangle(x + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), y,
						Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT));
			}
			for (int i = 0; i < 2; i++) {
				squareList.add(new Rectangle(x + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE),
						y + (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), Constant.BLOCK_WIDTH,
						Constant.BLOCK_HEIGHT));
			}
			break;
		case 6:
			squareList.add(new Rectangle(x + (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), y, Constant.BLOCK_WIDTH,
					Constant.BLOCK_HEIGHT));
			for (int i = 0; i < 3; i++) {
				squareList.add(new Rectangle(x + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE),
						y + (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), Constant.BLOCK_WIDTH,
						Constant.BLOCK_HEIGHT));
			}
			break;
		case 7:
			for (int i = 0; i < 2; i++) {
				squareList.add(new Rectangle(x + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), y,
						Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT));
			}
			for (int i = 1; i <= 2; i++) {
				squareList.add(new Rectangle(x + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE),
						y + (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), Constant.BLOCK_WIDTH,
						Constant.BLOCK_HEIGHT));
			}
			break;
		}
	}

	/**
	 * 键盘按下事件
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			rotate = true;
			down = left = right = false;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			down = true;
			rotate = left = right = false;
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			left = true;
			rotate = right = down = false;
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			right = true;
			rotate = left = down = false;
			break;
		}
	}

}
