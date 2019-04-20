package com.zzk.teris.constant;

import java.awt.Color;

public class Constant {
	public static final int FRAME_WIDTH = 950;
	public static final int FRAME_HEIGHT = 880;
	
	public static final int BLOCK_WIDTH = 40;
	public static final int BLOCK_HEIGHT = 40;
	public static final int BLOCK_SPACE = 2;

	public static final String IMG_PRE="com/zzk/teris/img/";//ͼƬ·��ǰ׺

	public static final int GAME_WIDTH = 42*14-3;
	public static final int GAME_HEIGHT = 800;
	public static final int GAME_X_LEFT= BLOCK_WIDTH+BLOCK_SPACE;
	public static final int GAME_X_RIGHT= GAME_X_LEFT+GAME_WIDTH;
	public static final int GAME_Y_UP = BLOCK_HEIGHT+BLOCK_SPACE;
	public static final int GAME_Y_DOWN = GAME_Y_UP+GAME_HEIGHT;

	public static final Color COLOR_BLOCKS[] = new Color[] { 
					new Color(255, 204, 0),//32
					new Color(153, 255, 153) ,//64
					new Color(204, 204, 0),//128
					new Color(255, 204, 102),//256
					new Color(255, 80, 80),//512
					new Color(102, 102, 255),//1024
					new Color(204, 0, 102),//2048
					new Color(153, 102, 0),//4096
					new Color(153, 0, 153)//8192
	};

}
