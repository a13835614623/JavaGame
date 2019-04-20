package com.neusoft.planewar.client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
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
	public Plane myPlane = new Plane(this, true);
	public List<EnemyPlane> enemyPlanes = new CopyOnWriteArrayList<>();
	public Background background = new Background(0, 4, "background_08");
	public List<Explode> explodes = new CopyOnWriteArrayList<>();
	public List<Missile> missiles = new CopyOnWriteArrayList<>();
	public List<Item> items = new CopyOnWriteArrayList<>();
	public List<MusicUtil> musics = new CopyOnWriteArrayList<>();


	public Random random = new Random();

	@Override
	public void launchFrame() {
		super.launchFrame();
		// Ìí¼Ó¼üÅÌ¼àÌýÆ÷
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
		PlaneWarClient pwc = this;
		new Thread() {
			@Override
			public void run() {
				while (true) {
					int r = random.nextInt(7);
					EnemyPlane enemyPlane = null;
					switch (r) {
					case 0://boss
						enemyPlane = new EnemyPlane(pwc, 1, 100, 100, false);
						enemyPlanes.add(enemyPlane);
						try {
							Thread.sleep(30000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						break;
					case 1:
						enemyPlane = new EnemyPlane(pwc, (int) (-400 + 100 * Math.random() * 6), 300, 1, false);
						enemyPlanes.add(enemyPlane);
						break;
					case 2:
						enemyPlane = new EnemyPlane(pwc, (int) (50 + 80 * Math.random() * 6), -100, 2, false);
						enemyPlanes.add(enemyPlane);
						break;
					default:
						enemyPlane = new EnemyPlane(pwc, 0, 0, r, false);
						enemyPlanes.add(enemyPlane);
						break;
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		new Thread(){
			@Override
			public void run() {
				while(true){
					int r = random.nextInt(7)+1;
					System.out.println(r);
					background=new Background(0, 4, "background_0"+r); 
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		new MusicUtil("bgm").start();
	}

	final long start = System.currentTimeMillis();

	@Override
	public void paint(Graphics g) {
		background.draw(g);
		myPlane.draw(g);
		if (!myPlane.live) {
			Image img1 = ImageUtil.images.get("fail");
			g.drawImage(img1, (Constant.GAME_WIDTH - img1.getWidth(null)) / 2,
					(Constant.GAME_HEIGHT - img1.getHeight(null)) / 2, null);
		}
		for (EnemyPlane enemyPlane : enemyPlanes) {
			enemyPlane.draw(g);
		}
		for (Missile missile : missiles) {
			missile.hitPlanes(enemyPlanes);
			missile.hitPlane(myPlane);
			missile.draw(g);
		}
		for (Explode explode : explodes) {
			explode.draw(g);
		}
		for (Item item : items) {
			item.draw(g);
			item.hitMyPlane(myPlane);
		}

	}

	public static void main(String[] args) {
		PlaneWarClient c = new PlaneWarClient();
		c.launchFrame();
	}
}
