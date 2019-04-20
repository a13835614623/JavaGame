package com.zzk.snake.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GameUtil {

	/**
	 * 根据图片的相对路径获取图片
	 * 
	 * @param imagePath
	 * @return 图片
	 */
	public static Image getImage(String imagePath) {
		URL url = GameUtil.class.getClassLoader().getResource(imagePath);
		BufferedImage img = null;
		try {
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	/**
	 * 按指定角度旋转图片
	 * @param bufferedimage
	 * @param degree
	 * @return 图片
	 */
	public static Image rotateImage(final BufferedImage bufferedimage, final int degree) {
		int w = bufferedimage.getWidth();// 得到图片宽度。
		int h = bufferedimage.getHeight();// 得到图片高度。
		int type = bufferedimage.getColorModel().getTransparency();// 得到图片透明度。
		BufferedImage img;// 空的图片。
		Graphics2D graphics2d;// 空的画笔。
		(graphics2d = (img = new BufferedImage(w, h, type)).createGraphics())
				.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);// 旋转，degree是整型，度数，比如垂直90度。
		graphics2d.drawImage(bufferedimage, 0, 0, null);// 从bufferedimagecopy图片至img，0,0是img的坐标。
		graphics2d.dispose();
		return img;// 返回复制好的图片，原图片依然没有变，没有旋转，下次还可以使用。
	}
}
