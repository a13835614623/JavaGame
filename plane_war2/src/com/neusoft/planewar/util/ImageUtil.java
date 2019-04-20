package com.neusoft.planewar.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.neusoft.planewar.constant.Constant;

/**
 * 存放项目中所有的图片<br>
 * 使用Map结构粗放时，使用静态代码块和静态初始化<br>
 * 
 * @author zzk
 *
 */
public class ImageUtil {
	public static Map<String, Image> images = new HashMap<>();

	static {
		// 背景图
		for (int i = 1; i <= 7; i++) {
			images.put("background_0" + i,
					GameUtil.getImage(Constant.IMG_PRE + "background/background_0" + i + ".png"));
		}
		// 我方1号飞机
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				images.put("myPlane_0" + i + "_0" + j,
						GameUtil.getImage(Constant.IMG_PRE + "plane/myPlane/myPlane_0" + i + "_0" + j + ".png"));
			}
		}
		// 我方1号飞机子弹
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				images.put("myPlane_missile_0" + i + "_0" + j, GameUtil
						.getImage(Constant.IMG_PRE + "missile/myPlane/myPlane_missile_0" + i + "_0" + j + ".png"));
			}
		}
		images.put("myPlane_missile_super",
				GameUtil.getImage(Constant.IMG_PRE + "missile/myPlane/myPlane_missile_super.png"));

		// 掉落物品
		for (int i = 1; i <= 6; i++) {
			images.put("item_0" + i, GameUtil.getImage(Constant.IMG_PRE + "item/item_0" + i + ".png"));
		}
		images.put("effect_04", GameUtil.getImage(Constant.IMG_PRE + "item/effect/effect_04.png"));

		images.put("myPlane_missile_05_01",
				GameUtil.getImage(Constant.IMG_PRE + "missile/myPlane/myPlane_missile_05_01.png"));
		images.put("myPlane_missile_05_02",
				GameUtil.getImage(Constant.IMG_PRE + "missile/myPlane/myPlane_missile_05_02.png"));
		images.put("myPlane_missile_05_03",
				GameUtil.getImage(Constant.IMG_PRE + "missile/myPlane/myPlane_missile_05_03.png"));

		// 我方血条
		images.put("myBlood_blank", GameUtil.getImage(Constant.IMG_PRE + "blood/myBlood_blank.png"));
		images.put("myBlood", GameUtil.getImage(Constant.IMG_PRE + "blood/myBlood.png"));
		// 积分
		images.put("score", GameUtil.getImage(Constant.IMG_PRE + "score/score.png"));
		// 警告
		images.put("warning", GameUtil.getImage(Constant.IMG_PRE + "warning.png"));
		images.put("success", GameUtil.getImage(Constant.IMG_PRE + "success.png"));
		images.put("fail", GameUtil.getImage(Constant.IMG_PRE + "fail.png"));

		// 敌人血条
		images.put("blood_blank", GameUtil.getImage(Constant.IMG_PRE + "blood/blood_blank.png"));
		images.put("blood", GameUtil.getImage(Constant.IMG_PRE + "blood/blood.png"));
		// 敌人子弹
		for (int i = 1; i <= 6; i++) {
			images.put("enemyPlane_missile_0" + i,
					GameUtil.getImage(Constant.IMG_PRE + "missile/enemyPlane/enemyPlane_missile_0" + i + ".png"));
		}
		// boss子弹
		images.put("enemyPlane_missile_0100",
				GameUtil.getImage(Constant.IMG_PRE + "missile/enemyPlane/enemyPlane_missile_0100.png"));

		// 爆炸
		for (int i = 1; i <= 8; i++) {
			images.put("explode_0" + i, GameUtil.getImage(Constant.IMG_PRE + "explode/explode_0" + i + ".png"));
		}

		// 敌方飞机1-5（动图）
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 4; j++) {
				images.put("enemyPlane_0" + i + "_0" + j,
						GameUtil.getImage(Constant.IMG_PRE + "plane/emenyPlane/enemyPlane_0" + i + "_0" + j + ".png"));
			}
		}
		// 敌方boss飞机
		for (int i = 1; i <= 5; i++) {
			images.put("enemyPlane_boss_0" + i,
					GameUtil.getImage(Constant.IMG_PRE + "plane/boss/enemyPlane_boss_0" + i + ".png"));
		}
	}
}
