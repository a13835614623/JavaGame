package com.zzk.teris.core.blocks;

import java.awt.Color;

import com.zzk.teris.constant.Constant;
import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.Square;

public class Block7 extends AbstractBlock{

	public Block7(int x, int y, Color color) {
		super(x, y, color);
	}

	@Override
	protected void createSquareList() {
		for (int i = 0; i < 2; i++) {
			getSquareList().add(new Square(getX() + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), getY(),
					Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT,getColor()));
		}
		for (int i = 1; i <= 2; i++) {
			getSquareList().add(new Square(getX() + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE),
					getY() + (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), Constant.BLOCK_WIDTH,
					Constant.BLOCK_HEIGHT,getColor()));
		}
	}
}
