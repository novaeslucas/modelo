package com.novaeslucas.modelo.internal.util;

import java.util.regex.Matcher;

public class Strings {

	public static String getString(final String key, final Object... params) {
		String result = null;

		if (key != null) {
			result = new String(key);
		}

		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				if (params[i] != null) {
					result = result.replaceAll("\\{" + i + "\\}", Matcher.quoteReplacement(params[i].toString()));
				}
			}
		}

		return result;
	}

	public static boolean isEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}
}
