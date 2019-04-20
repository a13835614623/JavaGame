package com.zzk.interpreter;

import java.awt.Graphics;
import java.awt.Point;

import com.zzk.AbstractGrammarInterpreter;
import com.zzk.util.DrawUtil;
/**
 * 解释器F
 * @author zzk
 */
public class GrammarInterpreterF extends AbstractGrammarInterpreter implements Cloneable{
	private boolean isSymmetricalTree = false;
	public GrammarInterpreterF() {
		super();
	}

	public GrammarInterpreterF(int length, String grammarString, Point startPoint, double startAngle, double rotateAngle) {
		super(length, grammarString, startPoint, startAngle, rotateAngle);
		if (length >= 100)
			isSymmetricalTree = true;// 长度为100，打开长度自减开关
	}
	/**
	 * 根据式子进行解释，绘图
	 * @param g
	 */
	@Override
	public void interpret(Graphics g) {
		if(grammarString==null) return;
		for (char ch : grammarString.toCharArray()) {
			switch (ch) {
			case 'F':// 画线
				startPoint = DrawUtil.drawLine(g, startPoint, startAngle, length);
				break;
			case '+':// 左转rotateAngle度
				startAngle += rotateAngle;
				break;
			case '-':// 右转rotateAngle度
				startAngle -= rotateAngle;
				break;
			case '[':// 保存当前状态
				try {
					stack.push((GrammarInterpreterF) this.clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				if (isSymmetricalTree)
					length -= 10;// 对称分形树时打开
				break;
			case ']':// 回到上一步
				GrammarInterpreterF d = (GrammarInterpreterF) stack.pop();
				this.startPoint = d.startPoint;
				this.startAngle = d.startAngle;
				this.length = d.length;
				break;
			}
		}
	}
}
