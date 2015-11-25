package com.m2i.formation.repositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFSingleton {
	
	private static EntityManagerFactory emf = null;
	
	public static EntityManagerFactory getEMF(){
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("FormationJPA2");
		}
		return emf;
	}
	
	private EMFSingleton() {
	}
	
}
