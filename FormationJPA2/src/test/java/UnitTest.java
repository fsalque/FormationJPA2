package test.java;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

import org.junit.Test;

import com.m2i.formation.media.entities.Media;
import com.m2i.formation.repositories.EMFSingleton;

public class UnitTest {

	@Test
	public void entityManagerTest() {
		EntityManagerFactory emf = EMFSingleton.getEMF();
		assertNotNull(emf);
		EntityManager em = emf.createEntityManager();
		assertNotNull(em);
	}
	
	@Test
	public void findTest() {
		EntityManagerFactory emf = EMFSingleton.getEMF();
		assertNotNull(emf);
		EntityManager em = emf.createEntityManager();
		assertNotNull(em);
		Media m = em.find(Media.class, 2);
		assertNotNull(m);
		assertEquals(m.getTitle(), "Les robots");
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		tx.rollback();
		
	}

}
