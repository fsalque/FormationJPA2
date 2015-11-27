package com.m2i.formation.services;

import com.m2i.formation.media.entities.Author;

public interface IMainService {

	void addAuthorToMedia(Author a, int mediaId) throws ServiceException;

}