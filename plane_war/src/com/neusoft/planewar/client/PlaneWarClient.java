package com.neusoft.planewar.client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.core.Background;
import com.neusoft.planewar.core.EnemyPlane;
import com.neusoft.planewar.core.Explode;
import com.neusoft.planewar.core.Item;
import com.neusoft.planewar.core.Missile;
import com.neusoft.planewar.core.MyFrame;
import com.neusoft.planewar.core.Plane;
import com.neusoft.planewar.util.ImageUtil;
import com.neusoft.planewar.util.MusicUtil;

public class PlaneWarClient extends MyFrame {
	Point center = new Point((Constant.GAME_WIDTH) / 2, (Constant.GAME_HEIGHT) / 2);
	public Plane myPlane = null;
	public List<EnemyPlane> enemyPlanes = new CopyOnWriteArrayList<>();
	public Background background = new Background(0, 4, "background_02");
	public List<Explode> explodes = new CopyOnWriteArrayList<>();
	public List<Missile> missiles = new CopyOnWriteArrayList<>();
	public List<Item> items = new CopyOnWriteArrayList<>();
	public List<MusicUtil> musics = new CopyOnWriteArrayList<>();

	public void generateEnemy() {

		// 12*1
		for (int i = 0; i < 6; i++) {// 6架小boss
			EnemyPlane enemyPlane = new EnemyPlane(this, -400 + 100 * i, 300, 1, false);
			enemyPlanes.add(enemyPlane);
		}
		for (int i = 0; i < 6; i++) {// 6架小boss
			EnemyPlane enemyPlane = new EnemyPlane(this, 50 + 80 * i, -100, 2, false);
			enemyPlanes.add(enemyPlane);
		}
		// 6*7
		enemyPlanes.add(new EnemyPlane(this, 0, 0, 5, false));// 1中
		for (int i = 0; i < 5; i++) {// 5架小boss
			EnemyPlane enemyPlane = new EnemyPlane(this, 0, 0, 3, false);
			enemyPlanes.add(enemyPlane);
		}
		enemyPlanes.add(new EnemyPlane(this, 0, 0, 6, false));// 1中
		for (int i = 0; i < 5; i++) {// 5架小boss
			EnemyPlane enemyPlane = new EnemyPlane(this, 0, 0, 4, false);
			enemyPlanes.add(enemyPlane);
		}
		enemyPlanes.add(new EnemyPlane(this, 0, 0, 5, false));// 1中
		for (int i = 0; i < 5; i++) {// 5架小boss
			EnemyPlane enemyPlane = new EnemyPlane(this, -400 + 100 * i, 300, 1, false);
			enemyPlanes.add(enemyPlane);
		}
		enemyPlanes.add(new EnemyPlane(this, 0, 0, 6, false));// 1中
		for (int i = 0; i < 5; i++) {// 5架小boss
			EnemyPlane enemyPlane = new EnemyPlane(this, 50 + 80 * i, -100, 2, false);
			enemyPlanes.add(enemyPlane);
		}
		enemyPlanes.add(new EnemyPlane(this, 0, 0, 5, false));// 1中
		for (int i = 0; i < 5; i++) {// 5架小boss
			EnemyPlane enemyPlane = new EnemyPlane(this, 0, 0, 3, false);
			enemyPlanes.add(enemyPlane);
		}
		enemyPlanes.add(new EnemyPlane(this, 0, 0, 6, false));// 1中
		for (int i = 0; i < 5; i++) {// 5架小boss
			EnemyPlane enemyPlane = new EnemyPlane(this, 0, 0, 4, false);
			enemyPlanes.add(enemyPlane);
		}
		enemyPlanes.add(new EnemyPlane(this, 0, 0, 5, false));// 1中
		for (int i = 0; i < 5; i++) {// 5架小boss
			EnemyPlane enemyPlane = new EnemyPlane(this, 0, 0, 3, false);
			enemyPlanes.add(enemyPlane);
		}
		// 8*2
		enemyPlanes.add(new EnemyPlane(this, 0, 0, 5, false));// 2中
		enemyPlanes.add(new EnemyPlane(this, 0, 0, 6, false));
		for (int i = 0; i < 6; i++) {// 6架小boss
			EnemyPlane enemyPlane = new EnemyPlane(this, -400 + 100 * i, 300, 1, false);
			enemyPlanes.add(enemyPlane);
		}
		enemyPlanes.add(new EnemyPlane(this, 0, 0, 5, false));// 2中
		enemyPlanes.add(new EnemyPlane(this, 0, 0, 6, false));
		for (int i = 0; i < 6; i++) {// 6架小boss
			EnemyPlane enemyPlane = new EnemyPlane(this, 50 + 80 * i, -100, 2, false);
			enemyPlanes.add(enemyPlane);
		}

		/*
		 * 大BOSS1
		 */
		enemyPlanes.add(new EnemyPlane(this, 1, 100, 100, false));// 1大

	}

	@Override
	public void launchFrame() {
		super.launchFrame();
		// 添加键盘监听器

		generateEnemy();

		new MusicUtil("bgm").start();
	}

	final long start = System.currentTimeMillis();

	@Override
	public void paint(Graphics g) {
		background.draw(g);
		if (enemyPlanes.size() > 0)
			first(g);
		else if (!myPlane.live) {
			Image img1 = ImageUtil.images.get("fail");
			g.drawImage(img1, (Constant.GAME_WIDTH - img1.getWidth(null)) / 2,
					(Constant.GAME_HEIGHT - img1.getHeight(null)) / 2, null);
		} else {
			Image img = ImageUtil.images.get("success");
			g.drawImage(img, (Constant.GAME_WIDTH - img.getWidth(null)) / 2,
					(Constant.GAME_HEIGHT - img.getHeight(null)) / 2, null);
		}
		if (myPlane != null) {
			for (Missile missile : missiles) {
				missile.hitPlanes(enemyPlanes);
				missile.hitPlane(myPlane);
				missile.draw(g);
			}
			myPlane.draw(g);
			for (Explode explode : explodes) {
				explode.draw(g);
			}
			for (Item item : items) {
				item.draw(g);
				item.hitMyPlane(myPlane);
			}

		}
	}

	public int index = 0;

	public void first(Graphics g) {
		long time = System.currentTimeMillis() - start;
		if (time >= 1950 && time < 2000) {// 2秒飞机出现
			myPlane = new Plane(this, true);
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					myPlane.keyPressed(e);
				}

				@Override
				public void keyReleased(KeyEvent e) {
					myPlane.keyReleased(e);
				}
			});
		}
		if (time >= 3950) {// 4秒12架小飞机
			for (index = 0; index < 12; index++) {
				enemyPlanes.get(index).draw(g);
			}
		}
		if (time >= 5950) {// 6秒 1个中BOSS（从上往下直线进入）和5个小BOSS进入画面（从右往左）
			for (index = 12; index < 18; index++) {
				enemyPlanes.get(index).draw(g);
			}
		}
		if (time >= 9950) {// 00：10
							// 1个中BOSS（从左往右）和5个小BOSS进入画面（从右上往左下）（1——6道具随机产生）
			for (index = 18; index < 24; index++) {
				enemyPlanes.get(index).draw(g);
			}
		}
		if (time >= 13950) {// 00：14
							// 1个中BOSS（从上往下正弦函数）和5个小BOSS进入画面（从右往左）
			for (index = 24; index < 30; index++) {
				enemyPlanes.get(index).draw(g);
			}
		}
		if (time >= 15950) {// 00：16 1个
							// 中BOSS（从左往右）和5个小BOSS进入画面（1——6道具随机产生）
			for (index = 30; index < 36; index++) {
				enemyPlanes.get(index).draw(g);
			}
		}
		if (time >= 17950) {// 00：18 1个
							// 中BOSS（从左往右）和5个小BOSS进入画面（1——6道具随机产生）
			for (index = 36; index < 42; index++) {
				enemyPlanes.get(index).draw(g);
			}
		}
		if (time >= 21950) {// 00：22
							// 1个中BOSS和5个小BOSS进入画面（1——6道具随机产生）
			for (index = 42; index < 48; index++) {
				enemyPlanes.get(index).draw(g);
			}
		}
		if (time >= 25950) {// 00：26 1个中BOSS和5个小BOSS进入画面
			for (index = 48; index < 54; index++) {
				enemyPlanes.get(index).draw(g);
			}
		}
		if (time >= 31950) {// 00：32 2个中BOSS和6个小BOSS进入画面
			for (index = 54; index < 62; index++) {
				enemyPlanes.get(index).draw(g);
			}
		}
		if (time >= 39950) {// 00：40 2个中BOSS和6个小BOSS进入画面（1——6道具随机产生）
			for (index = 62; index < 70; index++) {
				enemyPlanes.get(index).draw(g);
			}
		}
		if (time >= 45950 && time <= 47950) {// 00：48 警告
			Image warningImg = ImageUtil.images.get("warning");
			g.drawImage(warningImg, (Constant.GAME_WIDTH - warningImg.getWidth(null)) / 2, center.y, null);
		}
		if (time >= 55000) {// 00：50 进行提示：危险警报 ，敌方大BOSS来袭
			for (index = 70; index < 71; index++) {
				enemyPlanes.get(index).draw(g);
			}
		}
	}

	public static void main(String[] args) {
		PlaneWarClient c = new PlaneWarClient();
		c.launchFrame();
	}
}
