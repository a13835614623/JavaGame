package com.neusoft.planewar.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;

import javazoom.jl.player.Player;
/**
 * 音乐播放类
 * @author zzk
 *
 */
public class MusicUtil extends Thread {
	private boolean loop;
	private static String fileName;
	
	public MusicUtil(String fileName,boolean loop) {
		this.loop = loop;
		this.fileName = fileName;
	}

	public MusicUtil(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 根据是否循环播放音乐
	 */
	@Override
	public void run() {
		try{
			if(loop){
				while(true){
					play();
				}
			}else{
				play();
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	/**
	 * 根据音乐的相对路径获取音乐(mp3)
	 * @param fileName
	 * @return
	 */
	private void play(){
        Player p=null;
		try {   
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("src/com/neusoft/planewar/music/"+fileName+".mp3"));   
            p=new Player(buffer);
            p.play(); 
        } catch (Exception e) {   
             System.out.println(e);   
        }  
	}
}
