package com.zzk.teris.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import com.zzk.teris.client.TerisClient;
import com.zzk.teris.constant.Constant;
import com.zzk.teris.core.blocks.Block4;
import com.zzk.teris.core.state.ActiveState;
import com.zzk.teris.core.state.StopState;

/**
 * 块类
 * 
 * @author zzk
 */
public abstract class AbstractBlock implements Drawable {
	private static enum Direction{
		LEFT,RIGHT,DOWN,NORMAL,ROTATE;
	}
	private List<Square> squareList = new LinkedList<>();// 基本方块组成表
	private int x, y;// 坐标
	private Direction direction;// 块的方向状态
	private AbstractBlockState state;// 块的运动状态
	private int downSpeed = 1;// 下降的速度
	private Point center;// 块中心点位置
	private Color color;// 块颜色
	private int count = 1;// 旋转临时变量
	private int minX;// 块左边界
	private int minY;// 块上边界
	private int maxX;// 块右边界
	private int maxY;// 块下边界
	{
		MyFrame.sleepTime = 30;
		direction = Direction.NORMAL;//初始化方向
		state = new ActiveState(this);// 初始化为活跃态
	}
	public AbstractBlock(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		createSquareList();
		updateCenter();
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public List<Square> getSquareList() {
		return squareList;
	}


	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public AbstractBlockState getState() {
		return state;
	}

	public void setState(AbstractBlockState state) {
		this.state = state;
	}

	/**
	 * 旋转块
	 */
	private void rotate() {
		if (this instanceof Block4) {
			return;
		}
		for (Square square : squareList) {
			int dx = square.x - center.x;
			int dy = square.y - center.y;
			if (x <= Constant.GAME_X_LEFT) {
				square.setLocation(center.x - dy + Constant.BLOCK_SPACE
						+ count * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE) / 2, center.y + dx);
			} else if (maxX >= Constant.GAME_X_RIGHT - Constant.BLOCK_WIDTH / 2) {
				square.setLocation(center.x - dy - Constant.BLOCK_WIDTH * 2 - Constant.BLOCK_SPACE
						+ count * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE) / 2, center.y + dx);
			} else {
				square.setLocation(center.x - dy - Constant.BLOCK_WIDTH
						+ count * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE) / 2, center.y + dx);
			}
		}
		count = -count;
	}

	/**
	 * 处理左右方块间的碰撞
	 */
	private void handleCollision() {
		for (Square square : squareList) {
			switch (direction) {
			case LEFT:// 如果本块的方块的左测有其他方块,则停止
				if (square.getCenterX() == minX && (TerisClient.getSquare(square, 0, -1) != null || TerisClient.getSquare(square, 1, -1) != null
						|| TerisClient.getSquare(square, -1, -1) != null)) {
					direction = Direction.NORMAL;
				}
				break;
			case RIGHT:// 如果本块的方块的右测有其他方块,则停止
				if (square.getCenterX() == maxX && (TerisClient.getSquare(square, 0, 1) != null || TerisClient.getSquare(square, 1, 1) != null
						|| TerisClient.getSquare(square, -1, 1) != null)) {
					direction = Direction.NORMAL;
				}
				break;
			default:
				break;
			}
		}
	}

	public void move() {
		handleCollision();// 处理左右方块碰撞问题
		switch (direction) {// 根据方向状态处理
		case LEFT:
			x -= (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE);
			for (Square square : squareList) {// 遍历每个小块使其左移
				square.x -= (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE);
			}
			break;
		case RIGHT:
			x += (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE);
			for (Square square : squareList) {// 遍历每个小块使其右移
				square.x += (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE);
			}
			break;
		case DOWN:
			MyFrame.sleepTime /= 30;// 加快下落速度
			break;
		case ROTATE:
			rotate();// 旋转
			break;
		case NORMAL:
			break;
		}
		direction = Direction.NORMAL;// 恢复状态
		y += downSpeed;
		for (Square square : squareList) {// 自然下移
			square.y += downSpeed;
		}
	}

	@Override
	public void draw(Graphics g) {
		updateCenter();// 更新中心点坐标
		state.saveSquares();// 保存square
		state.draw(g);// 根据状态绘制
	}
	/**
	 * 处理下边界
	 */
	public void outOfBounds() {
		if (maxY == (Constant.GAME_Y_DOWN - Constant.BLOCK_HEIGHT / 2 - Constant.BLOCK_SPACE)) {
			state = new StopState(this);// 停止
		}
	}
	/**
	 * 更新中心点坐标
	 */
	private void updateCenter() {
		if (center == null)
			center = new Point();
		minX = maxX = (int) squareList.get(0).getCenterX();
		minY = maxY = (int) squareList.get(0).getCenterY();
		for (Square square : squareList) {
			int centerX = (int) square.getCenterX();
			int centerY = (int) square.getCenterY();
			minX = Math.min(centerX, minX);
			maxX = Math.max(centerX, maxX);
			minY = Math.min(centerY, minY);
			maxY = Math.max(centerY, maxY);
		}
		center.x = (minX + maxX) / 2;
		center.y = (minY + maxY) / 2;
		x=minX - Constant.BLOCK_WIDTH / 2;
		y=minY - Constant.BLOCK_HEIGHT / 2;
	}

	/**
	 * 填充squareList
	 */
	protected abstract void createSquareList();

	/**
	 * 键盘按下事件
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			direction = Direction.ROTATE;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			direction = Direction.DOWN;
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			if (x <= Constant.GAME_X_LEFT) {// 左边界
				direction = Direction.NORMAL;
			} else {
				direction = Direction.LEFT;
			}
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			if (maxX >= Constant.GAME_X_RIGHT - Constant.BLOCK_WIDTH / 2) {// 右边界
				direction = Direction.NORMAL;
			} else {
				direction = Direction.RIGHT;
			}
			break;
		}
	}

}
