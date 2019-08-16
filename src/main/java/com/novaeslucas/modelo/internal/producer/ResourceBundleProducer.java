package com.novaeslucas.modelo.internal.producer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import com.novaeslucas.modelo.internal.annotation.Bundle;
import com.novaeslucas.modelo.internal.util.ResourceBundle;

@ApplicationScoped
public class ResourceBundleProducer implements Serializable {

	private static final long serialVersionUID = -6326879086294232941L;
	
	private Map<String, ResourceBundle> cache = new HashMap<String, ResourceBundle>();
	
	@Inject
	private Locale locale;
	
	public ResourceBundle create(String name){
		ResourceBundle bundle = null;
		
		if (cache.containsKey(name)) {
			bundle = cache.get(name);

		} else {
			if(locale == null) { locale = Locale.getDefault(); }
			bundle = new ResourceBundle(ResourceBundle.getBundle(name, locale));
			cache.put(name, bundle);
		}
		
		return bundle;
	}
	
	@Produces
	@Bundle(name="")
	public ResourceBundle create(InjectionPoint injectionPoint){
		String name = injectionPoint.getAnnotated().getAnnotation(Bundle.class).name();
		return create(name);
	}
	
}
