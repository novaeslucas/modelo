package com.novaeslucas.modelo.internal.context;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;

import com.novaeslucas.modelo.internal.annotation.ViewScoped;
import com.novaeslucas.modelo.internal.util.Faces;

public class ViewContext implements Context {

	@Override
	public <T> T get(final Contextual<T> contextual) {
		return get(contextual, null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(final Contextual<T> contextual, final CreationalContext<T> creationalContext) {
		T instance = null;

		Bean<T> bean = (Bean<T>) contextual;
		Map<String, Object> viewMap = Faces.getViewMap();

		if (viewMap.containsKey(bean.getName())) {
			instance = (T) viewMap.get(bean.getName());

		} else if (creationalContext != null) {
			instance = bean.create(creationalContext);
			viewMap.put(bean.getName(), instance);
		}

		return instance;
	}

	@Override
	public Class<? extends Annotation> getScope() {
		return ViewScoped.class;
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
