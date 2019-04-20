package com.zzk.teris.core.state;

import java.awt.Graphics;
import java.util.List;

import com.zzk.teris.client.TerisClient;
import com.zzk.teris.constant.Constant;
import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.AbstractBlockState;
import com.zzk.teris.core.Square;

public class StopState extends AbstractBlockState{

	public StopState(AbstractBlock block) {
		super(block);
	}

	@Override
	public void saveSquares() {
		List<Square> squareList = block.getSquareList();
		for (Square square : squareList) {
			if (TerisClient.getSquare(square, 0, 0) == null) {
				TerisClient.squares[(Constant.GAME_Y_DOWN - square.y) / (Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE)
						+ 1][(square.x - Constant.GAME_X_LEFT) / (Constant.BLOCK_HEIGHT + Constant.BLOCK_SPACE)
								+ 1] = square.clone();
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		block.getState().saveSquares();// ±£´æsquare
		block.setState(new StopedState(getBlock()));;
	}

}
