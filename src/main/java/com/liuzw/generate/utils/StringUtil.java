package com.liuzw.generate.utils;


/**
 * @author liuzw
 */
public class StringUtil {


	private StringUtil() {
	}

	/**
	 *  去掉字符串中特殊字符
	 *
	 * @param inputString              字符串
	 * @param firstCharacterUppercase  首字母是否大写
	 * @return 						   字符串
	 */
	public static String getCamelCaseString(String inputString, boolean firstCharacterUppercase, boolean isColumn) {
		StringBuilder sb = new StringBuilder();
		boolean nextUpperCase = false;
		inputString = isColumn ? inputString.replaceFirst("t_", "") : inputString;
		for (int i = 0; i < inputString.length(); i++) {
			char c = inputString.charAt(i);
			switch (c) {
			case '_':
			case '-':
            case '@':
            case '$':
            case '#':
            case ' ':
            case '/':
            case '&':
				if (sb.length() > 0) {
					nextUpperCase = true;
				}
				break;
			default:
				if (nextUpperCase) {
					sb.append(Character.toUpperCase(c));
					nextUpperCase = false;
				} else {
					sb.append(Character.toLowerCase(c));
				}
				break;
			}
		}

		if (firstCharacterUppercase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}

		return sb.toString();
	}
}
