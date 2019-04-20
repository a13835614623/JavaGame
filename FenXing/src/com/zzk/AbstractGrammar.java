package com.zzk;

import java.util.HashMap;
/**
 * 抽象的语法分析迭代器
 * @author zzk
 */
public abstract class AbstractGrammar {
	private char start;// 起始符
	private char[] end;// 终结符集合
	private char[] notEnd;// 非终结符集合
	private HashMap<String, String[]> productMap;// 产生式规则
	
	public AbstractGrammar() {
		super();
	}
	public AbstractGrammar(char start, char[] end, char[] notEnd, HashMap<String, String[]> productMap) {
		super();
		this.start = start;
		this.end = end;
		this.notEnd = notEnd;
		this.productMap = productMap;
	}
	public char getStart() {
		return start;
	}
	public void setStart(char start) {
		this.start = start;
	}
	public char[] getEnd() {
		return end;
	}
	public void setEnd(char[] end) {
		this.end = end;
	}
	public char[] getNotEnd() {
		return notEnd;
	}
	public void setNotEnd(char[] notEnd) {
		this.notEnd = notEnd;
	}
	public HashMap<String, String[]> getProductMap() {
		return productMap;
	}
	public void setProductMap(HashMap<String, String[]> productMap) {
		this.productMap = productMap;
	}
	/**
	 * 参数是否合法
	 * @return
	 */
	public abstract boolean isLegal();
	/**
	 * 生成迭代n次之后的式子
	 */
	public abstract String product(int n);
}
