package com.liuzw.generate.utils;


/**
 * @author liuzw
 */
public class StringUtility {


	private StringUtility() {
	}

	public static String getCamelCaseString(String inputString,
			boolean firstCharacterUppercase, boolean isClassName) {
		StringBuilder sb = new StringBuilder();

		//将表名t_ 去掉
		inputString = isClassName ? inputString.replaceFirst("t_","") : inputString;

		boolean nextUpperCase = false;
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

	/**
	 * 首字母转小写
	 */
	public static String toLowerCaseFirstOne(String s){
		if(Character.isLowerCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}

	}


	/**
	 * 首字母转大写
	 */
	public static String toUpperCaseFirstOne(String s){
		if(Character.isUpperCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

}
