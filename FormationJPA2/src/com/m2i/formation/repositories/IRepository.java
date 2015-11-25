package com.m2i.formation.repositories;

import java.util.List;

import com.m2i.formation.media.entities.IEntity;

public interface IRepository<T extends IEntity> {
	public abstract List<T> getAll();
	public abstract T getByID(int id);
	public abstract T save(T entity);
	public abstract T update(T entity);
	public abstract void remove(T entity);
}
