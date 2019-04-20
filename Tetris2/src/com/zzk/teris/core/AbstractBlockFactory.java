package com.zzk.teris.core;

import java.awt.Color;
/**
 * ³éÏó¿é¹¤³§
 * @author zzk
 *
 */
public interface AbstractBlockFactory {
	public AbstractBlock getBlock(int x,int y,Color color);
}
