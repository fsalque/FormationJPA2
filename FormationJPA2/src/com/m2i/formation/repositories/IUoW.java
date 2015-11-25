package com.m2i.formation.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public interface IUoW {
	
	public abstract EntityManager getEntityManager();
	public abstract void setEntityManager(EntityManager em);
	public abstract EntityTransaction getTransaction();
	public abstract void setTransaction(EntityTransaction tr);
	public abstract void commit();

}
