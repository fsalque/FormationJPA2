package com.m2i.formation.media.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the page database table.
 * 
 */
@Entity
@Table(name="page")
@NamedQuery(name="Page.findAll", query="SELECT p FROM Page p")
public class Page implements Serializable,IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Media
	@ManyToOne
	@JoinColumn(name="id_Media")
	private Media media;

	public Page() {
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}