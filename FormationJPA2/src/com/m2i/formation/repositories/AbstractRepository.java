package com.m2i.formation.repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.m2i.formation.media.entities.IEntity;

public abstract class AbstractRepository<T extends IEntity> implements IRepository<T>, IUoW {

	private EntityManager em;
	
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public AbstractRepository() {
		ParameterizedType pt = (ParameterizedType)getClass().getGenericSuperclass();
		entityClass = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public void setEntityManager(EntityManager em) {
		this.em = em;

	}

	private EntityTransaction transaction;
	
	@Override
	public EntityTransaction getTransaction() {
		if(transaction == null){
			transaction = getEntityManager().getTransaction();
		}
		if(!transaction.isActive()){
			transaction.begin();
		}
		return transaction;
	}

	@Override
	public void setTransaction(EntityTransaction tr) {
		this.transaction = tr;

	}

	@Override
	public void commit() {
		this.getTransaction().commit();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		return getEntityManager().createQuery("select e from "+ entityClass.getSimpleName() + " e").getResultList();
	}

	@Override
	public T getByID(int id) {
		return getEntityManager().find(entityClass, id);
	}

	@Override
	public T save(T entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		getEntityManager().merge(entity);
		return entity;
	}

	@Override
	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));

	}
	
	@SuppressWarnings("unchecked")
	protected List<T> getByJPQL(String jpql){
		return getEntityManager().createQuery(jpql).getResultList();
	}

}
