package com.zzk.teris.core.blocks;

import java.awt.Color;

import com.zzk.teris.constant.Constant;
import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.Square;

public class Block2 extends AbstractBlock{

	public Block2(int x, int y, Color color) {
		super(x, y, color);
	}

	/**
	 * »­·½¿é
	 */
	@Override
	protected void createSquareList() {
		getSquareList().add(new Square(getX(), getY(), Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT,getColor()));
		for (int i = 0; i < 3; i++) {
			getSquareList().add(new Square(getX() + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE),
					getY() + Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE, Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT,getColor()));
		}
	}
}
