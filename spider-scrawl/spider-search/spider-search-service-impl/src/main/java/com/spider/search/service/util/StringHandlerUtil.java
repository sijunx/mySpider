package com.spider.search.service.util;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StringHandlerUtil {
	
	public static byte[] bt;
	public static InputStream is;
	public static Reader read;
	public static Lexeme t;
	public static IKSegmenter iks;
	
	/**
	 * input:str
	 * 将字符串分词转换成数组
	 */
	public List<String> stringToArray(String str) {
		
		List<String> list = new ArrayList<String>();
		bt = str.getBytes();
		is = new ByteArrayInputStream(bt);
		read = new InputStreamReader(is);
		iks = new IKSegmenter(read, true);
		try {
			while ((t = iks.next()) != null) {
				if(t.getLength() <= 1){
					continue;
				}
				list.add(t.getLexemeText());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
}
