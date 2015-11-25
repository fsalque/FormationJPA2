package com.m2i.formation.repositories;

import com.m2i.formation.media.entities.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class MediaRepository implements IRepository<Media>, IUoW {

	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return this.entityManager;
	}

	@Override
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;

	}

	private EntityTransaction transaction;
	
	@Override
	public EntityTransaction getTransaction() {
		// TODO Auto-generated method stub
		return this.transaction;
	}

	@Override
	public void setTransaction(EntityTransaction tr) {
		this.transaction = tr;

	}

	@Override
	public void commit() {
		this.getTransaction().commit();

	}

	@Override
	public List<Media> getAll() {
		Query query = getEntityManager().createQuery("select e from Media e");
		@SuppressWarnings("unchecked")
		List<Media> l = (List<Media>)query.getResultList();
		return l;
	}

	@Override
	public Media getByID(int id) {
		return getEntityManager().find(Media.class, id);
	}

	@Override
	public Media save(Media entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public Media update(Media entity) {
		getEntityManager().merge(entity);
		return entity;
	}

	@Override
	public void remove(Media entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
		
	}


}
