package com.novaeslucas.modelo.internal.util;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.novaeslucas.modelo.internal.annotation.ViewScoped;
import com.novaeslucas.modelo.internal.api.Parameter;


public class ParameterImpl<T> implements Parameter<T>, Serializable {

	private static final long serialVersionUID = 1L;

	private Class<Object> type;

	private Converter converter;

	private final InjectionPoint ip;

	private final HttpServletRequest request;

	private T value;

	private final String key;

	@Inject	
	public ParameterImpl(final InjectionPoint ip, HttpServletRequest request) {
		this.ip = ip;
		this.request = request;
		
		this.key = ip.getMember().getName();
		
		this.type = Reflections.getGenericTypeArgument(ip.getMember(), 0);
		this.converter = Faces.getConverter(type);
	}

	public String getKey() {
		return key;
	}

	private boolean isSessionScoped() {
		return ip.getAnnotated().isAnnotationPresent(SessionScoped.class);
	}

	private boolean isViewScoped() {
		return ip.getAnnotated().isAnnotationPresent(ViewScoped.class);
	}

	private boolean isRequestScoped() {
		return ip.getAnnotated().isAnnotationPresent(RequestScoped.class);
	}

	@SuppressWarnings("unchecked")
	public T getValue() {
		T result = null;
		String parameterValue = request.getParameter(key);

		if (isSessionScoped()) {
			if (parameterValue != null) {
				request.getSession().setAttribute(key, Faces.convert(parameterValue, converter));
			}

			result = (T) request.getSession().getAttribute(key);

		} else if (isRequestScoped()) {
			result = (T) Faces.convert(parameterValue, converter);

		} else if (isViewScoped()) {
			Map<String, Object> viewMap = Faces.getViewMap();
			if (parameterValue != null) {
				viewMap.put(key, Faces.convert(parameterValue, converter));
			}

			result = (T) viewMap.get(key);

		} else {
			if (value == null) {
				value = (T) Faces.convert(parameterValue, converter);
			}

			result = value;
		}

		return result;
	}

	public Converter getConverter() {
		return converter;
	}

	public void setValue(T value) {
		if (isSessionScoped()) {
			this.request.getSession().setAttribute(key, value);

		} else if (isRequestScoped()) {
			// FIXME Lançar exceção informando que não é possível setar parâmetros no request.

		} else if (isViewScoped()) {
			Map<String, Object> viewMap = Faces.getViewMap();
			viewMap.put(key, value);

		} else {
			this.value = value;
		}
	}
}
