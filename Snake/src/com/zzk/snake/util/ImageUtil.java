package com.zzk.snake.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.zzk.snake.constant.Constant;

public class ImageUtil {
	public static Map<String,Image> images = new HashMap<>();
	
	static{
		images.put("snake_body", GameUtil.getImage(Constant.IMG_PRE+"snake_body.png"));
		images.put("food", GameUtil.getImage(Constant.IMG_PRE+"food.png"));
		images.put("snake_head", GameUtil.getImage(Constant.IMG_PRE+"snake_head.png"));
		images.put("background", GameUtil.getImage(Constant.IMG_PRE+"background.jpg"));
		images.put("fail", GameUtil.getImage(Constant.IMG_PRE+"fail.png"));
	}
}
