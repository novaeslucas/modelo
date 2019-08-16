package com.novaeslucas.modelo.internal.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Generics {

	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericSuperclass(Class<?> clazz, int index) {
		Type type = clazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) type;
			return (Class<T>) paramType.getActualTypeArguments()[index];
		}
		return null;
	}

	public static <T> Class<T> getGenericSuperclass(Class<?> clazz) {
		return getGenericSuperclass(clazz, 0);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericInterfaces(Class<?> clazz, int index) {
		Type[] types = clazz.getGenericInterfaces();
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;
				return (Class<T>) paramType.getActualTypeArguments()[index];
			}
		}
		return null;
	}
	
	public static <T> Class<T> getGenericInterfaces(Class<?> clazz) {
		return getGenericInterfaces(clazz, 0);
	}

	
}
