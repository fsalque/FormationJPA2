package test.java;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;

import com.m2i.formation.media.entities.Author;
import com.m2i.formation.media.entities.Media;
import com.m2i.formation.media.entities.Publisher;
import com.m2i.formation.repositories.AuthorRepository;
import com.m2i.formation.repositories.EMFSingleton;
import com.m2i.formation.repositories.Media2Repository;
import com.m2i.formation.repositories.PublisherRepository;
import com.m2i.formation.repositories.SpringSingleton;
import com.m2i.formation.services.MainService;
import com.m2i.formation.services.ServiceException;

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
	
	@Test
	public void ServiceTest() {
		MainService ms = new MainService();
		Author a = new Author();
		a.setName("Albert");
		a.setSurName("Nonyme");
		try {
			ms.addAuthorToMedia(a, 2);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EntityManagerFactory emf = EMFSingleton.getEMF();
		assertNotNull(emf);
		EntityManager em = emf.createEntityManager();
		assertNotNull(em);
		
		AuthorRepository arepo = new AuthorRepository();
		arepo.setEntityManager(em);
		assertEquals(true,arepo.getAll().contains(a));
	}
	
	@Test
	public void AddRemoveTest() {
		EntityManagerFactory emf = EMFSingleton.getEMF();
		assertNotNull(emf);
		EntityManager em = emf.createEntityManager();
		assertNotNull(em);
		Media m = new Media();
		m.setPrice(12);
		m.setTitle("yolow");
		Media2Repository mrepo = new Media2Repository();
		mrepo.setEntityManager(em);
		mrepo.getTransaction();
		mrepo.save(m);
		mrepo.commit();
		assertEquals(true,mrepo.getAll().contains(m));
		mrepo.getTransaction();
		Media mDb = mrepo.getByID(m.getId());
		mrepo.remove(mDb);
		mrepo.commit();
		assertEquals(false,mrepo.getAll().contains(m));
	}
	
	@Test
	public void AddBookToAuthorTest() {
		EntityManagerFactory emf = EMFSingleton.getEMF();
		assertNotNull(emf);
		EntityManager em = emf.createEntityManager();
		assertNotNull(em);
		
		Media2Repository mrepo = new Media2Repository();
		mrepo.setEntityManager(em);

		AuthorRepository arepo = new AuthorRepository();
		arepo.setEntityManager(em);
		
		Author a = new Author();
		a.setName("Albert");
		a.setSurName("Nonyme");
		
		Media m = new Media();
		m.setPrice(12);
		m.setTitle("yolow");
		
		arepo.getTransaction();
		arepo.save(a);
		arepo.commit();
		assertEquals(true,arepo.getAll().contains(a));
		
		a.getMedias().add(m);
		arepo.getTransaction();
		arepo.save(a);
		arepo.commit();
		assertEquals(true,mrepo.getAll().contains(m));
		
		mrepo.getTransaction();
		mrepo.remove(m);
		mrepo.commit();
		assertEquals(false,mrepo.getAll().contains(m));
		
		arepo.getTransaction();
		arepo.remove(a);
		arepo.commit();
		assertEquals(false,arepo.getAll().contains(a));
	}
	
	@Test
	public void ServicePublisherTest() {
		MainService ms = new MainService();
		Publisher p = new Publisher();
		p.setNom("Albert");
		try {
			ms.setPublisherToMedia(p, 2);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EntityManagerFactory emf = EMFSingleton.getEMF();
		assertNotNull(emf);
		EntityManager em = emf.createEntityManager();
		assertNotNull(em);
		
		PublisherRepository prepo = new PublisherRepository();
		prepo.setEntityManager(em);
		assertEquals(true,prepo.getAll().contains(p));
	}

	@Test
	public void SpringTest() {
		ApplicationContext factory = SpringSingleton.getFactory();
		Media m = (Media)factory.getBean("media");
		assertNotNull(m);
		Media2Repository mr = factory.getBean("mediaRepository",Media2Repository.class);
		assertNotNull(mr);
		MainService ms = factory.getBean("mainService",MainService.class);
		assertNotNull(ms);
	}
}
