package com.liuzw.generate.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author liuzw
 */
public class JdbcTypeUtils {
	private static Properties config;

	static {
		InputStream in = JdbcTypeUtils.class.getClassLoader()
				.getResourceAsStream("dataType_2_JdbcType.properties");
		config = new Properties();
		try {
			config.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("No AreaPhone.properties defined error");
		}
	}

	/**
	 * 根据key读取value
	 */
	public static String readValue(String key) {
		try {
			return config.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
