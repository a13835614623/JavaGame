package com.zzk.grammar;

import java.util.HashMap;
import java.util.Random;

import com.zzk.AbstractGrammar;

/**
 * 语法分析迭代器
 * 随机右部，一次迭代次数为key的个数
 * @author zzk
 */
public class RandomValueGrammar extends AbstractGrammar{
	public RandomValueGrammar() {
		super();
	}
	public RandomValueGrammar(char start, char[] end, char[] notEnd, HashMap<String, String[]> productMap) {
		super(start, end, notEnd, productMap);
	}
	public boolean isLegal(){
		return false;
	}
	/**
	 * 每个key随机应用一个value，迭代产生字符串
	 */
	@Override
	public String product(int n) {
		if (n < 0)
			return null;
		String result = new String(getStart()+"");
		Random r = new Random();
		HashMap<String, String[]> productMap = getProductMap();
		if (n == 0)
			return result;
		Object[] keys = productMap.keySet().toArray();//键数组
		for (int i = 0; i < n; i++) {//迭代
			for (int j = 0,len=keys.length; j <len; j++) {//遍历产生式map，每个key随机应用一个value
				String key = (String) keys[j];//key
				String[] values = productMap.get(key);//得到values数组
				result = result.replace(key, values[r.nextInt(values.length)]);//随机应用一个产生式
			}
		}
		System.out.println(result);
		return result;
	}

}
