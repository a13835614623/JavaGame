package com.zzk.teris.core.blocks;

import java.awt.Color;

import com.zzk.teris.constant.Constant;
import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.Square;

public class Block4 extends AbstractBlock{

	public Block4(int x, int y, Color color) {
		super(x, y, color);
	}

	@Override
	protected void createSquareList() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				getSquareList().add(new Square(getX() + i * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE),
						getY() + j * (Constant.BLOCK_WIDTH + Constant.BLOCK_SPACE), Constant.BLOCK_WIDTH,
						Constant.BLOCK_HEIGHT,getColor()));
			}
		}
	}
}
