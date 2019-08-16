package com.novaeslucas.modelo.internal.api;

import java.io.Serializable;
import java.util.List;

public interface Service<T> extends Serializable {
	
	T findById(Object id);
	List<T> findAll();
	List<T> findAllOrderBy(String... columns);
	void insert(T entity);
	void update(T entity);
	void remove(T entity);
	Long count();
	List<T> top(int count);
	List<T> findInterval(Integer firstResult, Integer maxResult);

}
