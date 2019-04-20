package com.zzk.teris.core.state;

import java.awt.Graphics;

import com.zzk.teris.client.TerisClient;
import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.AbstractBlockState;
import com.zzk.teris.core.Square;

public class ActiveState extends AbstractBlockState {

	public ActiveState(AbstractBlock block) {
		super(block);
	}

	@Override
	public void saveSquares() {
		for (Square square : block.getSquareList()) {
			Square squareNext = TerisClient.getSquare(square, -1, 0);
			if (squareNext != null) {
				block.setState(new StopState(block));
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		block.move();// 移动
		block.outOfBounds();// 处理越界问题
		for (Square square : block.getSquareList()) {//绘制
			square.draw(g);
		}
	}

}
