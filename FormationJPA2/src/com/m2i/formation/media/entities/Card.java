package com.m2i.formation.media.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the card database table.
 * 
 */
@Entity
@Table(name="card")
@NamedQuery(name="Card.findAll", query="SELECT c FROM Card c")
public class Card implements Serializable,IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-many association to Media
	@ManyToMany
	@JoinTable(
		name="mediacard"
		, joinColumns={
			@JoinColumn(name="id_Card", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id", nullable=false)
			}
		)
	private List<Media> medias;

	public Card() {
	}

	@Override
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public List<Media> getMedias() {
		return this.medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

}