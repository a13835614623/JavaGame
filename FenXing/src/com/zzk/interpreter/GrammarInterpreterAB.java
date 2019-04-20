package com.zzk.interpreter;

import java.awt.Graphics;
import java.awt.Point;

import com.zzk.AbstractGrammarInterpreter;
import com.zzk.util.DrawUtil;
/**
 * 解释器AB
 * @author zzk
 */
public class GrammarInterpreterAB extends AbstractGrammarInterpreter implements Cloneable{
	public GrammarInterpreterAB() {
		super();
	}

	public GrammarInterpreterAB(int length, String grammarString, Point startPoint, double startAngle, double rotateAngle) {
		super(length, grammarString, startPoint, startAngle, rotateAngle);
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
			case 'A':// 画线
				startPoint = DrawUtil.drawLine(g, startPoint, startAngle, length);
				break;
			case 'B':
				startPoint = DrawUtil.drawLine(g, startPoint, startAngle, length);
				break;
			case '[':// 保存状态并左转rotateAngle度
				try {
					stack.push((GrammarInterpreterAB) this.clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				startAngle -= rotateAngle;
				break;
			case '('://保存状态并右转rotateAngle度
				try {
					stack.push((GrammarInterpreterAB) this.clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				startAngle += rotateAngle;
				break;
			case ']'://回到上一步
			case ')':
				GrammarInterpreterAB d = (GrammarInterpreterAB) stack.pop();
				this.startPoint = d.startPoint;
				this.startAngle = d.startAngle;
				this.length = d.length;
				break;
			}
		}
	}
}
