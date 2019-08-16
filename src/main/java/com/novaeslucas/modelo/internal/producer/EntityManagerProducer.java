package com.novaeslucas.modelo.internal.producer;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceUnit;

@RequestScoped
public class EntityManagerProducer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceUnit(name="modelo", unitName="modelo")
	private EntityManagerFactory emf;
	
	private EntityManager em;
	
	@Produces
	@Default
	@RequestScoped
	public EntityManager create(){
		
		if(em == null){
			em = emf.createEntityManager();
			em.setFlushMode(FlushModeType.AUTO);
		}
		
		return em;
	}
	
	public void dispose(@Disposes EntityManager em){
		if(em != null && em.isOpen()){
			em.close();
		}
	}

}
