package com.zzk.teris.core.factory;

import java.awt.Color;

import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.AbstractBlockFactory;
import com.zzk.teris.core.blocks.Block2;
/**
 * ¿é¹¤³§
 * @author zzk
 */
public class Block2Factory implements AbstractBlockFactory{
	@Override
	public AbstractBlock getBlock(int x, int y, Color color) {
		return new Block2(x, y,color);
	}
}
