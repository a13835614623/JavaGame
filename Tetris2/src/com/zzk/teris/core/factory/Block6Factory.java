package com.zzk.teris.core.factory;

import java.awt.Color;

import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.AbstractBlockFactory;
import com.zzk.teris.core.blocks.Block6;
/**
 * ¿é¹¤³§
 * @author zzk
 */
public class Block6Factory implements AbstractBlockFactory{
	@Override
	public AbstractBlock getBlock(int x, int y, Color color) {
		return new Block6(x,y,color);
	}
}
