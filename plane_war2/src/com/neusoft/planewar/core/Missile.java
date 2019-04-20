package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.util.ImageUtil;
import com.neusoft.planewar.util.MusicUtil;

public class Missile extends PlaneWarObject {
	boolean live;
	int speed;
	int type;
	public Missile() {
		super();
	}

	public Missile(PlaneWarClient pwc, int x, int y, String imageName,boolean good) {
		this.live = true;
		this.x = x;
		this.y = y;
		this.img = ImageUtil.images.get(imageName);
		this.width = img.getWidth(null);
		this.height = img.getWidth(null);
		this.pwc = pwc;
		this.speed=10;
		this.good=good;
	}
	public Missile(PlaneWarClient pwc, int x, int y, String imageName,int type,boolean good) {
		this(pwc, x, y, imageName,good);
		this.type=type;
	}


	public void setTheta(int theta) {
		this.theta = theta;
	}

	private int theta;
	/**
	 * 子弹击打飞机的方法
	 */
	public boolean hitPlane(Plane p){
		if(this.getRectangle().intersects(p.getRectangle())&&this.good!=p.isGood()&&p.live){
			this.live=false;
			if(p.level>=1){
				p.blood-= 10*p.level;
			}else{
				p.blood-= 20;
			}
			pwc.missiles.remove(this);
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 子弹击打飞机的方法
	 */
	public boolean hitPlanes(List<EnemyPlane> enemyPlanes){
		for (EnemyPlane enemyPlane : enemyPlanes) {
			if(this.hitPlane(enemyPlane)){
				return true;
			}
		}
		return false;
	}
	@Override
	public void move() {
		switch (type) {
		case 100://boss子弹1
			x+=Math.sin(Math.toRadians(theta))*speed;
			y-=Math.cos(Math.toRadians(theta))*speed;
			break;
		case 101://boss子弹2
			x-=Math.sin(Math.toRadians(theta))*speed*2;
			y-=Math.cos(Math.toRadians(theta))*speed;
			break;
		case 102://boss子弹3
			x+=Math.sin(Math.toRadians(theta))*speed;
			y+=Math.cos(Math.toRadians(theta))*speed*2;
			break;
		case 104://boss子弹4
			x-=Math.sin(Math.toRadians(theta))*speed;
			y+=Math.cos(Math.toRadians(theta))*speed*2;
			break;
		case 1:
			y += speed;
			break;
		case 2:
			y+=speed;
			break;
		case 3:
			y += speed;
			break;
		case 4:
			y+=speed;
			break;
		case 5:
			y+=speed;
			break;
		case 6://super
			x+=Math.sin(Math.toRadians(theta))*speed;
			y-=Math.cos(Math.toRadians(theta))*speed;
			break;
		case 7:
			x-=speed/4;
			y-=speed;
			break;
		case 8:
			y-=speed;
			break;
		case 9:
			x+=speed/4;
			y-=speed;
			break;
		default:
			y -= speed;
			break;
		}
		outOfBounds();
	}

	/**
	 * 子弹出界
	 */
	private void outOfBounds() {
		if ((x >= (Constant.GAME_WIDTH-width) || x <= 0) || (y >= (Constant.GAME_HEIGHT - height) || y <= 0)) {
			this.live = false;
			this.pwc.missiles.remove(this);
		}
	}

}
