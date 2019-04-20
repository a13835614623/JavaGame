package com.zzk.teris.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Square implements Drawable,Cloneable{
	Rectangle rect;
	Color color;
	
	public Square(Rectangle rect, Color color) {
		super();
		this.rect = rect;
		this.color = color;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((rect == null) ? 0 : rect.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (rect == null) {
			if (other.rect != null)
				return false;
		} else if (!rect.equals(other.rect))
			return false;
		return true;
	}

	@Override
	public  Square clone() throws CloneNotSupportedException {
		Square square = new Square((Rectangle) rect.clone(), color);
		return square;
	}
	
}
