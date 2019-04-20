package com.zzk.teris.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.zzk.teris.constant.Constant;


public class ImageUtil {
	public static Map<String,Image> images = new HashMap<>();
	
	static{
		images.put("fail", GameUtil.getImage(Constant.IMG_PRE+"fail.png"));
	}
}
