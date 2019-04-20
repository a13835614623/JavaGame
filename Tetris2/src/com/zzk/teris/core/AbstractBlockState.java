package com.zzk.teris.core;

import java.awt.Graphics;

/**
 * @author zzk
 *
 */
public abstract class AbstractBlockState implements Drawable{
	protected AbstractBlock block;
	public AbstractBlockState(AbstractBlock block) {
		super();
		this.block = block;
	}
	public AbstractBlock getBlock() {
		return block;
	}

	public void setBlock(AbstractBlock block) {
		this.block = block;
	}
	public abstract void saveSquares();
	public abstract void draw(Graphics g);
}
