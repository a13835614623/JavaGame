package com.zzk;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Stack;

/**
 * 抽象解释器
 * @author zzk
 */
public abstract class AbstractGrammarInterpreter implements Cloneable{
	protected Point startPoint;// 起始位置
	protected double startAngle;// 起始角度
	protected double rotateAngle;// 旋转角
	protected int length;// 线段长度
	protected String grammarString;// 语法式
	protected Stack<AbstractGrammarInterpreter> stack = new Stack<>();// 状态栈

	public AbstractGrammarInterpreter() {
		super();
	}

	public AbstractGrammarInterpreter(int length, String grammarString, Point startPoint, double startAngle, double rotateAngle) {
		super();
		this.length = length;
		this.grammarString = grammarString;
		this.startPoint = startPoint;
		this.startAngle = startAngle;
		this.rotateAngle = rotateAngle;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public double getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}

	public double getRotateAngle() {
		return rotateAngle;
	}

	public void setRotateAngle(double rotateAngle) {
		this.rotateAngle = rotateAngle;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getGrammarString() {
		return grammarString;
	}

	public void setGrammarString(String grammarString) {
		this.grammarString = grammarString;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public String toString() {
		return "AbstractGrammarInterpreter [startPoint=" + startPoint + ", startAngle=" + startAngle + ", rotateAngle="
				+ rotateAngle + ", length=" + length +", stack=" + stack + "]";
	}
	/**
	 * 解释
	 * @param g
	 */
	public abstract void interpret(Graphics g);
}
