package com.liuzw.generate.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *@author 刘泽伟
 */
public class SystemUtils {
	private static Properties config = null;

	static {
		InputStream in = SystemUtils.class.getClassLoader()
				.getResourceAsStream("properties/applications.properties");
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
