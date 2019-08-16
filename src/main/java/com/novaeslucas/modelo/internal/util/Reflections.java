package com.novaeslucas.modelo.internal.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.novaeslucas.modelo.internal.exception.InternalException;

public class Reflections {

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericTypeArgument(final Class<?> clazz, final int idx) {
		final Type type = clazz.getGenericSuperclass();

		ParameterizedType paramType;
		try {
			paramType = (ParameterizedType) type;
		} catch (ClassCastException cause) {
			paramType = (ParameterizedType) ((Class<T>) type).getGenericSuperclass();
		}

		return (Class<T>) paramType.getActualTypeArguments()[idx];
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericTypeArgument(final Field field, final int idx) {
		final Type type = field.getGenericType();
		final ParameterizedType paramType = (ParameterizedType) type;

		return (Class<T>) paramType.getActualTypeArguments()[idx];
	}

	public static <T> Class<T> getGenericTypeArgument(final Member member, final int idx) {
		Class<T> result = null;

		if (member instanceof Field) {
			result = getGenericTypeArgument((Field) member, idx);
		} else if (member instanceof Method) {
			result = getGenericTypeArgument((Method) member, idx);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericTypeArgument(final Method method, final int pos) {
		return (Class<T>) method.getGenericParameterTypes()[pos];
	}
	
	public static Field getField(Class<?> classe, String fieldName) {
		while (classe != null) {
			Field[] fields = classe.getDeclaredFields();

			if (fields != null && fields.length > 0) {
				for (Field f : fields) {
					if (fieldName.equals(f.getName()))
						return f;
				}
			}
			classe = classe.getSuperclass();
		}
		return null;
	}
	
	public static boolean hasField(Class<?> classe, String fieldName) {
		return getField(classe, fieldName) != null;
	}

	public static Object getFieldValue(Field field, Object object) {
		Object result = null;

		try {
			boolean acessible = field.isAccessible();
			field.setAccessible(true);
			result = field.get(object);
			field.setAccessible(acessible);

		} catch (Exception e) {
			throw new InternalException(e.getMessage(), e);
		}

		return result;
	}

	public static void setFieldValue(Field field, Object object, Object value) {
		try {
			boolean acessible = field.isAccessible();
			field.setAccessible(true);
			field.set(object, value);
			field.setAccessible(acessible);

		} catch (Exception e) {
			throw new InternalException(e.getMessage(), e);
		}
	}
	
	public static List<Field> findFieldsWithAnnotation(Class<?> clazz, Class<?> annotation) {
		List<Field> result = new ArrayList<Field>();

		while (clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (Field f : fields) {
					if (hasAnnotation(f, annotation))
						result.add(f);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return result;
	}
	
	public static boolean hasAnnotation(AnnotatedElement element, Class<?> annotation) {
		return getAnnotation(element, annotation) != null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getAnnotation(AnnotatedElement element, Class<T> annotation) {
		Annotation[] annotations = element.getDeclaredAnnotations();
		if (annotations != null && annotations.length > 0) {
			for (Annotation a : annotations) {
				if (annotation.equals(a.annotationType())) {
					return (T) a;
				}
			}
		}
		return null;
	}
	
	public static void setFieldValue(Object object, Field field, Object value) {
		try {
			boolean changedAccessibility = false;
			if (!field.isAccessible()) {
				field.setAccessible(true);
				changedAccessibility = true;
			}

			field.set(object, value);

			if (changedAccessibility) {
				field.setAccessible(false);
			}
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível alterar o valor do atributo " + field.getName() + ": " + e.getMessage());
		}

	}
	
	public static void setFieldValue(Object object, String fieldName, Object value) {
		Field field = getField(object.getClass(), fieldName);
		setFieldValue(object, field, value);
	}
	
	public static Object getFieldValue(Object object, Field field) {
		try {
			boolean changedAccessibility = false;
			if (!field.isAccessible()) {
				field.setAccessible(true);
				changedAccessibility = true;
			}

			Object value = field.get(object);
			
			if (changedAccessibility) {
				field.setAccessible(false);
			}

			return value;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possível pegar o valor do atributo " + field.getName() + ": " + e.getMessage());
		}

	}

	public static Object getFieldValue(Object object, String nomeAtributo) {
		return getFieldValue(object, getField(object.getClass(), nomeAtributo));
	}
	
	public static List<Method> findMethodsWithAnnotation(Class<?> clazz, Class<?> annotation) {
		List<Method> result = new ArrayList<Method>();

		while (clazz != null) {
			Method[] methods = clazz.getDeclaredMethods();
			if (methods != null && methods.length > 0) {
				for (Method m : methods) {
					if (hasAnnotation(m, annotation))
						result.add(m);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T clone(Class<?> objectClass, T object ){
		T newEntity = null;
		try {
			newEntity = (T) objectClass.newInstance();
			for (Field field : objectClass.getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(object);
				if(!Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())){
					field.set(newEntity, value);
				}
			}
			
		} catch (Exception e) {
			throw new InternalException(e.getMessage(), e);
		}
		
		return newEntity;
	}

}
