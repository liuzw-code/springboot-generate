package com.liuzw.generate.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author liuzw
 */
public class JavaTypeUtils {
	private static Properties config;

	static {
		InputStream in = JavaTypeUtils.class.getClassLoader()
				.getResourceAsStream("jdbcType_2_javaType.properties");
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
			System.err.println("ConfigInfoError" + e.toString());
			return null;
		}
	}


}
