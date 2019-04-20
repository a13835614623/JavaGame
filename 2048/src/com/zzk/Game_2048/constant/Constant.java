package com.zzk.Game_2048.constant;

import java.awt.Color;

public class Constant {
	public static final int GAME_WIDTH = 900;
	public static final int GAME_HEIGHT = 850;

	public static final int BACK_X = 150;
	public static final int BACK_Y = 150;

	public static final int BACK_WIDTH = 600;
	public static final int BACK_HEIGHT = 600;
	
	public static final int BLOCK_SPACE = 20;
	public static final int BLOCK_WIDTH = (BACK_WIDTH - 5 * BLOCK_SPACE) / 4;
	public static final int BLOCK_HEIGHT = (BACK_HEIGHT - 5 * BLOCK_SPACE) / 4;
	
	public static final int BACK_UP_IN_Y = BACK_Y+BLOCK_SPACE;
	public static final int BACK_DOWN_IN_Y = BACK_Y + BACK_HEIGHT-BLOCK_SPACE;
	public static final int BACK_LEFT_IN_X = BACK_X +BLOCK_SPACE;
	public static final int BACK_RIGHT_IN_X = BACK_X + BACK_WIDTH-BLOCK_SPACE;

	

	public static final String IMG_PRE = "com/zzk/Game_2048/img/";

	public static final Color COLOR_BACK = new Color(187, 173, 160);
	public static final Color COLOR_BLOCK_BACK = new Color(205, 192, 180);

	public static final Color COLOR_BLOCKS_ACTIVE[] = new Color[] { 
					new Color(238, 228, 218), //2
					new Color(237, 224, 200),//4
					new Color(242, 177, 121), //8
					new Color(245, 149, 99), //16
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
