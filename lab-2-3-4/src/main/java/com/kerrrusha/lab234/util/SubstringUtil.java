package com.kerrrusha.lab234.util;

import org.apache.commons.lang3.StringUtils;

public class SubstringUtil {

	public static String substringBefore(String value, String rightBounder) {
		return StringUtils.substringBefore(value, rightBounder).trim();
	}

	public static String substringBefore(String value, String rightBounder, boolean caseSensitive) {
		if (caseSensitive) {
			return substringBefore(value, rightBounder);
		}

		final int caseInsensitiveSubstringPos = StringUtils.indexOfIgnoreCase(value, rightBounder);
		if (caseInsensitiveSubstringPos <= 0) {
			return value;
		}
		return StringUtils.substring(value, 0, caseInsensitiveSubstringPos);
	}

	public static String substringAfter(String value, String leftBounder) {
		if (value.contains(leftBounder)) {
			value = StringUtils.substringAfter(value, leftBounder).trim();
		}
		return value.trim();
	}

	public static String substringAfterLast(String value, String leftBounder) {
		if (value.contains(leftBounder)) {
			value = StringUtils.substringAfterLast(value, leftBounder).trim();
		}
		return value;
	}

	public static String substringBetween(String value, String leftBounder, String rightBounder) {
		if (value.contains(leftBounder) && value.contains(rightBounder)) {
			try {
				return StringUtils.substringBetween(value, leftBounder, rightBounder).trim();
			} catch (NullPointerException ignored) {
				return "";
			}
		}

		return "";
	}
}
