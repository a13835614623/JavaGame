package com.zzk.Game_2048.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Rectangle;

import com.zzk.Game_2048.constant.Constant;

public class Block implements Drawable, Moveable {

	public static final int DIRECTION_NONE = 0;
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_DOWN = -1;
	public static final int DIRECTION_LEFT = 2;
	public static final int DIRECTION_RIGHT = -2;
	
	public int direction = DIRECTION_NONE;// 默认静止
	private String value;
	public int xOrder;// 方块坐标（非像素坐标）
	public int yOrder;// 方块坐标
	public int x;// 像素坐标
	public int y;//
	public static final int SPEED = 5;
	int level;
	boolean live;
	public int directionX = 0;
	public int directionY = 0;
	public BlockLoader loader =null;
	public Block(int xOrder, int yOrder, int level,BlockLoader loader) {
		super();
		this.xOrder = xOrder;
		this.yOrder = yOrder;
		this.level = level;
		this.value = (int) Math.pow(2, level) + "";
		if (level == 0)
			this.live = false;
		else
			this.live = true;
		this.x = Constant.BACK_Y + Constant.BLOCK_SPACE * xOrder + Constant.BLOCK_WIDTH * (xOrder - 1);
		this.y = Constant.BACK_X + Constant.BLOCK_SPACE * yOrder + Constant.BLOCK_WIDTH * (yOrder - 1);
		this.loader=loader;
	}
	public void keyPressed(int newDirection) {
		this.direction = newDirection;
		switch (direction) {
		case DIRECTION_UP:
			directionX = 0;
			directionY = -1;
			break;
		case DIRECTION_DOWN:
			directionX = 0;
			directionY = 1;
			break;
		case DIRECTION_LEFT:
			directionX = -1;
			directionY = 0;
			break;
		case DIRECTION_RIGHT:
			directionX = 1;
			directionY = 0;
			break;
		}
		updateOrder();
	}
	
	@Override
	public void draw(Graphics g) throws CloneNotSupportedException {
		updateOrder();
		updateState();
		switch (state) {
		case STATE_UNKOWN:
		case STATE_MERGE:
			break;
		case STATE_MOVING:
		case STATE_FREE:
			move();// 移动
			break;
		}
		// 画内容
		drawContent(g);
	}

	/**
	 * 画内容
	 * 
	 * @param g
	 */
	public void drawContent(Graphics g) {
		this.value = (int) Math.pow(2, level) + "";
		g.setColor(Constant.COLOR_BLOCKS_ACTIVE[level - 1]);
		Font f = new Font("微软雅黑", Font.BOLD, 60 - 5 * value.length());
		g.setFont(f);
		FontMetrics fm = new Label().getFontMetrics(f);
		int xValueStart = x + (Constant.BLOCK_WIDTH - fm.stringWidth(value)) / 2;
		int yValueStart = y+Constant.BLOCK_HEIGHT- (Constant.BLOCK_HEIGHT - fm.getHeight()) / 2-fm.getDescent();

		// 画背景
		g.fillRoundRect(x, y, Constant.BLOCK_WIDTH, Constant.BLOCK_WIDTH, 10, 10);
		g.setColor(Color.BLACK);
		// 画数字
		g.drawString(value, xValueStart, yValueStart);

//		 //测试记录
//		 f = new Font("微软雅黑", Font.BOLD, 20);
//		 g.setFont(f);
//		 String s = "(" + x + "," + y + ")" + moveCount;
//		 g.drawString(s, x, y + 20);
	}

	/**
	 * 移动
	 */
	int moveCount = 0;
	// 移动次数最大值
	public static final int MOVE_COUNT_MAX = (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE) / SPEED;

	/**
	 * 移动
	 */
	@Override
	public void move() {
		// 0到MOVE_COUNT_MAX
		switch (direction) {
		case DIRECTION_UP:
			y -= SPEED;
			break;
		case DIRECTION_DOWN:
			y += SPEED;
			break;
		case DIRECTION_LEFT:
			x -= SPEED;
			break;
		case DIRECTION_RIGHT:
			x += SPEED;
			break;
		}
		moveCount++;
		if (moveCount >= MOVE_COUNT_MAX) {
			moveCount = 0;
		}
		// 1到MOVE_COUNT_MAX
	}
	public int state=STATE_UNKOWN;
	public static final int STATE_UNKOWN = 0;// 未知态（判断态）
	public static final int STATE_MOVING = 1;// 移动态
	public static final int STATE_FREE = 2;// 自由态
	public static final int STATE_MERGE = 3;// 合并态

	/**
	 * 获得状态
	 * @return
	 */
	public int updateState() {
		if (moveCount != 0) {
			state = STATE_MOVING;
		} else if (moveCount == 0) {
			state = STATE_UNKOWN;
		}
		if (direction != DIRECTION_NONE && getNextBlock() == null) {
			state = STATE_FREE;
		}
		Block b = (Block) getNextBlock();
		//合并态判断
		if (direction != DIRECTION_NONE && b != null && b.level == level && (b.state != STATE_FREE)) {
			this.live = false;
			b.level++;
			b.state=STATE_MERGE;
			setThisBlock(null);
			BlockLoader.score += b.level * 10;
			if(BlockLoader.score>=BlockLoader.bestScore){
				BlockLoader.bestScore=BlockLoader.score;
			}
			if(b.getNextBlock()!=null&&b.getNextBlock().live&&b.getNextBlock().state==STATE_FREE){
			}else{
				b.direction=DIRECTION_NONE;
				b.directionX=0;
				b.directionY=0;
			}
			
		}
		return state;
	}
	/**
	 * 更新order
	 * 
	 * @throws CloneNotSupportedException
	 */
	public void updateOrder() {
		if (moveCount == 0) {// 判断态
			xOrder = (x - Constant.BACK_LEFT_IN_X) / (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE) + 1;
			yOrder = (y - Constant.BACK_UP_IN_Y) / (Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE) + 1;
			loader.blocks[xOrder][yOrder] = this;
			if (direction != 0 && getLastBlock() != null && getLastBlock().live && (getLastBlock() == this))
				setLastBlock(null);// 设置历史轨迹为空
		}
	}

	public void setLastBlock(Block block) {
		loader.blocks[xOrder - directionX][yOrder - directionY] = block;
	}

	public Block getLastBlock() {
		return loader.blocks[xOrder - directionX][yOrder - directionY];
	}

	public Block getNextBlock() {
		return loader.blocks[xOrder + directionX][yOrder + directionY];
	}
	public void setThisBlock(Block block){
		loader.blocks[xOrder][yOrder]=block;
	}
	/**
	 * 返回block对应的矩形
	 * 
	 * @return
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT);
	}

	/**
	 * 判断是否在边界
	 * 
	 * @return
	 */
	boolean xIsBounds = (x == (Constant.BACK_RIGHT_IN_X - Constant.BLOCK_WIDTH)) || (x == Constant.BACK_LEFT_IN_X);
	boolean yIsBounds = (y == (Constant.BACK_DOWN_IN_Y - Constant.BLOCK_HEIGHT)) || (y == Constant.BACK_UP_IN_Y);

	public boolean isAtBounds() {
		if ((xIsBounds || yIsBounds)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否在角落
	 * 
	 * @return
	 */
	public boolean isAtCorner() {
		if ((xIsBounds && yIsBounds)) {
			return true;
		}
		return false;
	}

	/**
	 * 处理出界问题
	 */
	public void outOfBounds() {
		if (x > (Constant.BACK_RIGHT_IN_X - Constant.BLOCK_WIDTH)) {
			x = Constant.BACK_RIGHT_IN_X - Constant.BLOCK_WIDTH;
		}
		if (x < Constant.BACK_LEFT_IN_X) {
			x = Constant.BACK_LEFT_IN_X;
		}
		if (y == (Constant.BACK_DOWN_IN_Y - Constant.BLOCK_HEIGHT)) {
			y = Constant.BACK_DOWN_IN_Y - Constant.BLOCK_HEIGHT;
		}
		if (y == Constant.BACK_UP_IN_Y) {
			y = Constant.BACK_UP_IN_Y;
		}
	}

	@Override
	public String toString() {
		String s = "value=" + value + " (" + xOrder + "," + yOrder + "),Direction=";
		switch (direction) {
		case DIRECTION_UP:
			s += "↑";
			break;
		case DIRECTION_DOWN:
			s += "↓";
			break;
		case DIRECTION_LEFT:
			s += "←";
			break;
		case DIRECTION_RIGHT:
			s += "→";
			break;
		case DIRECTION_NONE:
			s += "0";
			break;
		default:
			s += "方向异常";
			break;
		}
		if (getNextBlock() == null)
			s += ",block=null(" + (xOrder + directionX) + "," + (yOrder + directionY) + ")";
		else
			s += ",block=(" + getNextBlock().xOrder + "," + getNextBlock().yOrder + ")";
		return s + "(state=" + state + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + direction;
		result = prime * result + directionX;
		result = prime * result + directionY;
		result = prime * result + level;
		result = prime * result + (live ? 1231 : 1237);
		result = prime * result + moveCount;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + x;
		result = prime * result + xOrder;
		result = prime * result + y;
		result = prime * result + yOrder;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (direction != other.direction)
			return false;
		if (directionX != other.directionX)
			return false;
		if (directionY != other.directionY)
			return false;
		if (level != other.level)
			return false;
		if (live != other.live)
			return false;
		if (moveCount != other.moveCount)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (x != other.x)
			return false;
		if (xOrder != other.xOrder)
			return false;
		if (y != other.y)
			return false;
		if (yOrder != other.yOrder)
			return false;
		return true;
	}

}
