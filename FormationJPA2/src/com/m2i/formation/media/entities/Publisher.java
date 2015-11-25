package com.m2i.formation.media.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the publisher database table.
 * 
 */
@Entity
@Table(name="publisher")
@NamedQuery(name="Publisher.findAll", query="SELECT p FROM Publisher p")
public class Publisher implements Serializable,IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=25)
	private String nom;

	//bi-directional many-to-one association to Media
	@OneToMany(mappedBy="publisher")
	private List<Media> medias;

	public Publisher() {
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Media> getMedias() {
		return this.medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

	public Media addMedia(Media media) {
		getMedias().add(media);
		media.setPublisher(this);

		return media;
	}

	public Media removeMedia(Media media) {
		getMedias().remove(media);
		media.setPublisher(null);

		return media;
	}

}