package com.zzk.teris.core.state;

import java.awt.Graphics;

import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.AbstractBlockState;

public class StopedState extends AbstractBlockState{

	public StopedState(AbstractBlock block) {
		super(block);
	}


	@Override
	public void saveSquares() {
		
	}

	@Override
	public void draw(Graphics g) {
		
	}

}
