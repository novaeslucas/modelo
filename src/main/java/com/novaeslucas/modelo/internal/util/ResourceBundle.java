package com.novaeslucas.modelo.internal.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Set;

public class ResourceBundle extends java.util.ResourceBundle implements Serializable {

	private static final long serialVersionUID = 7883293427633615587L;

	private transient java.util.ResourceBundle delegate;

	public ResourceBundle(java.util.ResourceBundle resourceBundle) {
		this.delegate = resourceBundle;
	}

	@Override
	public boolean containsKey(String key) {
		return delegate.containsKey(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return delegate.getKeys();
	}

	@Override
	public Locale getLocale() {
		return delegate.getLocale();
	}

	@Override
	public Set<String> keySet() {
		return delegate.keySet();
	}

	public String getString(String key, Object... params) {
		return Strings.getString(getString(key), params);
	}

	@Override
	protected Object handleGetObject(String key) {
		Object result;

		try {
			Method method = delegate.getClass().getMethod("handleGetObject", String.class);

			method.setAccessible(true);
			result = method.invoke(delegate, key);
			method.setAccessible(false);

		} catch (Exception cause) {
			throw new RuntimeException(cause);
		}
		return result;
	}
	
}
