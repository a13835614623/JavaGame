package com.zzk.grammar;

import java.util.HashMap;
import java.util.Random;

import com.zzk.AbstractGrammar;

/**
 * 语法分析迭代器 左右部全随机，每次迭代只应用一个产生式规则
 * 
 * @author zzk
 */
public class RandomKVGrammar extends AbstractGrammar {
	public RandomKVGrammar() {
		super();
	}

	public RandomKVGrammar(char start, char[] end, char[] notEnd, HashMap<String, String[]> productMap) {
		super(start, end, notEnd, productMap);
	}

	public boolean isLegal() {
		return false;
	}

	/**
	 * 随机key，随机value,迭代产生字符串
	 */
	@Override
	public String product(int n) {
		if (n < 0)
			return null;
		String result = new String(getStart() + "");
		Random r = new Random();
		HashMap<String, String[]> productMap = getProductMap();
		if (n == 0)
			return result;
		Object[] keys = productMap.keySet().toArray();// 键数组
		for (int i = 0; i < n; i++) {// 迭代
			String key = (String) keys[r.nextInt(keys.length)];// 随机获取一个键
			String[] values = productMap.get(key);// 得到产生式数组
			String value = values[r.nextInt(values.length)];
			result = result.replace(key, value);// 随机应用一个产生式
		}
		System.out.println("result:"+result);
		return result;
	}

}
