package com.zzk.grammar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.zzk.AbstractGrammar;

import java.util.Random;
import java.util.Set;

/**
 * 语法分析迭代器
 * 一个key对应一个value，每次迭代应用每个key的value
 * @author zzk
 */
public class SingleKVGrammar extends AbstractGrammar {

	public SingleKVGrammar() {
		super();
	}

	public SingleKVGrammar(char start, char[] end, char[] notEnd, HashMap<String, String[]> productMap) {
		super(start, end, notEnd, productMap);
	}

	@Override
	public boolean isLegal() {
		HashMap<String, String[]> map = getProductMap();
		Set<Entry<String, String[]>> entrys = map.entrySet();
		Iterator<Entry<String, String[]>> iterator = entrys.iterator();
		Entry<String, String[]> entry=null;
		while(iterator.hasNext()){
			entry = iterator.next();
			String key = entry.getKey();
			String[] value = entry.getValue();
			if(!key.contains(getStart()+"")||value==null||value.length<1){//如果产生式左部不包含起始符，右部长度小于1
				return false;
			}
		}
		return false;
	}

	@Override
	public String product(int n) {
		if (n < 0)
			return null;
		String result = new String(getStart()+"");
		Random r = new Random();
		HashMap<String, String[]> productMap = getProductMap();
		if (n == 0)
			return result;
		Object[] keys = productMap.keySet().toArray();// key数组
		for (int i = 0; i < n; i++) {// 迭代
			for (Object key : keys) {
				String[] values = productMap.get(key);// 得到values右部数组
				result = result.replace((String) key, values[0]);//替换
			}
		}
		System.out.println(result);
		return result;
	}
}
