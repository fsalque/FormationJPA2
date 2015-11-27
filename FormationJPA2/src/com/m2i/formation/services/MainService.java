package com.m2i.formation.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.m2i.formation.media.entities.Author;
import com.m2i.formation.media.entities.Media;
import com.m2i.formation.media.entities.Publisher;
import com.m2i.formation.repositories.AuthorRepository;
import com.m2i.formation.repositories.EMFSingleton;
import com.m2i.formation.repositories.Media2Repository;

public class MainService implements IMainService {
	
	private Media2Repository mediaRepository;
	private AuthorRepository authorRepository;
	
	/* (non-Javadoc)
	 * @see com.m2i.formation.services.IMainService#addAuthorToMedia(com.m2i.formation.media.entities.Author, int)
	 */
	@Override
	public void addAuthorToMedia(Author a, int mediaId) throws ServiceException{
		EntityManagerFactory emf = EMFSingleton.getEMF();
		EntityManager em = emf.createEntityManager();
		mediaRepository = new Media2Repository();
		mediaRepository.setEntityManager(em);
		Media m = mediaRepository.getByID(mediaId);
		if(m != null){
			if(!m.getAuthors().contains(a)){
				m.getAuthors().add(a);
				mediaRepository.getTransaction();
				mediaRepository.save(m);
				mediaRepository.commit();
			}
			else{
				throw new ServiceException("Author already associated with the media.");
			}
		}
		else{
			throw new ServiceException("The media doesn't exist.");
		}
	}
	
	public void setPublisherToMedia(Publisher p, int mediaId) throws ServiceException{
		EntityManagerFactory emf = EMFSingleton.getEMF();
		EntityManager em = emf.createEntityManager();
		mediaRepository = new Media2Repository();
		mediaRepository.setEntityManager(em);
		Media m = mediaRepository.getByID(mediaId);
		if(m != null){
			m.setPublisher(p);
		}
		else{
			throw new ServiceException("The media doesn't exist.");
		}
	}

	public Media2Repository getMediaRepository() {
		return mediaRepository;
	}

	public void setMediaRepository(Media2Repository mediaRepository) {
		this.mediaRepository = mediaRepository;
	}

	public AuthorRepository getAuthorRepository() {
		return authorRepository;
	}

	public void setAuthorRepository(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
}
