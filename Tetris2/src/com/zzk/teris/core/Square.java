package com.zzk.teris.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * 基础方块类，用于组成每个不同形状的块
 * @author zzk
 */
public class Square extends Rectangle implements Drawable,Cloneable{
	private static final long serialVersionUID = 1L;
	private Color color;
	public Square(int x,int y,int width,int height,Color color) {
		super(x, y, width, height);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * 绘制方块
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}
	@Override
	public Square clone() {
		return new Square(x, y, width, height, new Color(color.getRGB()));
	}
}
