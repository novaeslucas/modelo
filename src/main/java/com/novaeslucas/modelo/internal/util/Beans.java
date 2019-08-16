package com.novaeslucas.modelo.internal.util;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

public class Beans {

	private static BeanManager manager;

	public static void setBeanManager(BeanManager beanManager) {
		manager = beanManager;
	}

	public static BeanManager getBeanManager() {
		return manager;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getReference(final Class<T> beanClass) {
		Bean<?> bean = manager.getBeans(beanClass).iterator().next();
		return (T) getReference(bean);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getReference(String beanName) {
		Bean<?> bean = manager.getBeans(beanName).iterator().next();
		return (T) getReference(bean);
	}

	@SuppressWarnings("unchecked")
	private static <T> T getReference(Bean<?> bean) {
		return (T) manager.getReference(bean, bean.getBeanClass(), manager.createCreationalContext(bean));
	}
}
