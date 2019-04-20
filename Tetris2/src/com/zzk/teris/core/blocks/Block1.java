package com.zzk.teris.core.blocks;

import java.awt.Color;

import com.zzk.teris.constant.Constant;
import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.Square;

public class Block1 extends AbstractBlock{

	public Block1(int x, int y, Color color) {
		super(x, y, color);
	}

	/**
	 * »­·½¿é
	 */
	@Override
	protected void createSquareList() {
		for (int i = 0; i < 4; i++) {
			getSquareList().add(new Square(getX() + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), getY(),
					Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT,getColor()));
		}
	}
}
