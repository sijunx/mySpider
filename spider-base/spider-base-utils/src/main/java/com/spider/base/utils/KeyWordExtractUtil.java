package com.spider.base.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class KeyWordExtractUtil {

	private final static Logger logger = LoggerFactory.getLogger(KeyWordExtractUtil.class);
	
	public static byte[] bt;
	public static InputStream is;
	public static Reader read;
	public static Lexeme t;
	public static IKSegmenter iks;
	
	/**
	 * input:str
	 * 将字符串分词转换成数组
	 */
	public static List<String> stringToArray(String str) {
		
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
			logger.warn("将字符串分词转换成数组异常 e:{}", ExceptionUtils.getStackTrace(e));
		}
		return list;
	}
}
