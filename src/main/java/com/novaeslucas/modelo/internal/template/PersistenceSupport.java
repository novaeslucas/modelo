package com.novaeslucas.modelo.internal.template;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;

import com.novaeslucas.modelo.internal.api.Persistence;
import com.novaeslucas.modelo.internal.util.Generics;

public abstract class PersistenceSupport<T> implements Persistence<T> {

	private static final long serialVersionUID = -6690001332635525263L;

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private Logger logger;
	
	
	public T findById(Object id) {
		return getEntityManager().find(getEntityClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		StringBuilder jql = new StringBuilder();
		jql.append("from " + getEntityClass().getSimpleName());
		Query query = getEntityManager().createQuery(jql.toString());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAllOrderBy(String... columns) {
		StringBuilder jql = new StringBuilder();
		jql.append("from " + getEntityClass().getSimpleName() + " order by ");
		
		for (String column : columns) {
			jql.append(" " + column + ",");
		}
		
		Query query = getEntityManager().createQuery(jql.toString().substring(0, jql.toString().length() - 1));
		return query.getResultList();
	}

	public void insert(T entity) {
		getEntityManager().persist(entity);
	}

	public void update(T entity) {
		getEntityManager().merge(entity);
	}

	public void remove(T entity) {
		getEntityManager().remove(entity);
	}

	public Long count() {
		StringBuilder jql = new StringBuilder();
		jql.append("select count(*) from " + getEntityClass().getSimpleName());
		return count(jql.toString(), null);	
	}
	
	protected Long count(String jql, Map<String,?> params){
		Query query = getEntityManager().createQuery(jql.toString());
		getParams(query, params);
		return (Long) query.getSingleResult();
	}

	public List<T> top(int count) {
		StringBuilder jql = new StringBuilder();
		jql.append(" from " + getEntityClass().getSimpleName());
		return findInterval(jql.toString(), null, null, count);
	}
	
	protected List<T> top(String jql, Map<String, ?> params, int maxResult) {
		return findInterval(jql, params, null, maxResult);
	}
	
	public List<T> findInterval(Integer firstResult, Integer maxResult){
		StringBuilder jql = new StringBuilder();
		jql.append("from " + getEntityClass().getSimpleName());
		return findInterval(jql.toString(), null, firstResult, maxResult);
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findInterval(String jql, Map<String, ?> params, Integer firstResult, Integer maxResult) {
		Query query = getEntityManager().createQuery(jql);
		if(firstResult != null) query.setFirstResult(firstResult); 
		if(maxResult != null) query.setMaxResults(maxResult);
		getParams(query, params);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> find(String jql, Map<String, ?> params){
		Query query = getEntityManager().createQuery(jql);
		getParams(query, params);
		return query.getResultList();
	}
	
	protected List<T> find(String jql){
		return find(jql, null);
	}
	
	protected CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}
	
	protected CriteriaQuery<T> createCriteriaQuery(){
		return getCriteriaBuilder().createQuery(getEntityClass());
	}
	
	protected EntityManager getEntityManager(){
		logger.debug("persistence.factory.create " + entityManager);
		return this.entityManager;
	}
	
	private void getParams( Query query, Map<String, ?> params){
		if (params != null) {
            Iterator<String> keys = params.keySet().iterator();
            
            while (keys.hasNext()) {
               String key = new String();
               key = keys.next();
               query.setParameter(key, params.get(key));
            }
         }
	}
	
	private Class<T> getEntityClass(){
		return Generics.getGenericSuperclass(this.getClass());
	}
	
	protected List<T> findByExample(final T example) {
		final CriteriaQuery<T> criteria = createCriteriaByExample(example);
		return getEntityManager().createQuery(criteria).getResultList();
	}
	
	private CriteriaQuery<T> createCriteriaByExample(final T example) {

		final CriteriaBuilder builder = getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery(getEntityClass());
		final Root<T> entity = query.from(getEntityClass());

		final List<Predicate> predicates = new ArrayList<Predicate>();
		final Field[] fields = example.getClass().getDeclaredFields();

		for (Field field : fields) {

			if (!field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Basic.class)
					&& !field.isAnnotationPresent(Enumerated.class)) {
				continue;
			}

			Object value = null;

			try {
				field.setAccessible(true);
				value = field.get(example);
			} catch (IllegalArgumentException e) {
				continue;
			} catch (IllegalAccessException e) {
				continue;
			}

			if (value == null) {
				continue;
			}

			final Predicate pred = builder.equal(entity.get(field.getName()), value);
			predicates.add(pred);
		}
		return query.where(predicates.toArray(new Predicate[0])).select(entity);
	}
}
