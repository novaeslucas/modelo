package com.novaeslucas.modelo.internal.template;

import java.util.List;

import com.novaeslucas.modelo.internal.api.Persistence;
import com.novaeslucas.modelo.internal.api.Service;


public class ServiceSupport<T, E extends Persistence<T>> implements Service<T> {
	
	private static final long serialVersionUID = 1L;
	
	private E persistence;
			
	public T findById(Object id) {
		return (T) getPersistence().findById(id);
	}

	public List<T> findAll() {
		return getPersistence().findAll();
	}

	public List<T> findAllOrderBy(String... columns) {
		return getPersistence().findAllOrderBy(columns);
	}

	public void insert(T entity) {
		getPersistence().insert(entity);
	}

	public void update(T entity) {
		getPersistence().update(entity);
	}

	public void remove(T entity) {
		getPersistence().remove(entity);
	}

	public Long count() {
		return getPersistence().count();
	}

	public List<T> top(int count) {
		return getPersistence().top(count);
	}
	
	public List<T> findInterval(Integer firstResult, Integer maxResult){
		return getPersistence().findInterval(firstResult, maxResult);
	}
	
	protected E getPersistence(){
		return persistence;
	}
	
	protected void setPersistence(E persistence){
		this.persistence = persistence;
	}
	
}
