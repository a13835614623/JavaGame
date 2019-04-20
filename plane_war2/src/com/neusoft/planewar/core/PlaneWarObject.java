package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.neusoft.planewar.client.PlaneWarClient;

public abstract class PlaneWarObject implements Drawable,Moveable{
	//建立关系(大关系)
	//调停者设计模式
	public PlaneWarClient pwc;
	public int x;
	public int y;
	public Image img;
	public int width;
	public int height;
	public boolean good;
	/**
	 * 所有飞机大战中统一的画的方法
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
	}
	/**
	 * 判断是敌方还是己方
	 */
	
	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}
	/**
	 * 让子类自己实现自己的move方法
	 */
	@Override
	public abstract void move();
	/**
	 * 获取子弹对应的矩形
	 */
	public Rectangle getRectangle(){
		return new Rectangle(x, y, width, height);
	}
}
