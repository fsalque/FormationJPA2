package com.m2i.formation.media.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media database table.
 * 
 */
@Entity
@Table(name="media")
@NamedQuery(name="Media.findAll", query="SELECT m FROM Media m")
public class Media implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	private Integer category;

	@Temporal(TemporalType.DATE)
	private Date dateParution;

	@Column(length=25)
	private String langue;

	private Integer nbPages;

	private Integer nbTracks;

	private Integer numISBN;

	@Column(nullable=false)
	private float price;

	@Column(nullable=false, length=50)
	private String title;

	//bi-directional many-to-one association to Publisher
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_Publisher")
	private Publisher publisher;

	//bi-directional many-to-many association to Author
	@ManyToMany(mappedBy="medias",cascade=CascadeType.ALL)
	private List<Author> authors;

	//bi-directional many-to-many association to Card
	@ManyToMany(mappedBy="medias")
	private List<Card> cards;

	//bi-directional many-to-one association to Page
	@OneToMany(mappedBy="media")
	private List<Page> pages;

	public Media() {
	}


	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Date getDateParution() {
		return this.dateParution;
	}

	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}

	public String getLangue() {
		return this.langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public Integer getNbPages() {
		return this.nbPages;
	}

	public void setNbPages(Integer nbPages) {
		this.nbPages = nbPages;
	}

	public Integer getNbTracks() {
		return this.nbTracks;
	}

	public void setNbTracks(Integer nbTracks) {
		this.nbTracks = nbTracks;
	}

	public Integer getNumISBN() {
		return this.numISBN;
	}

	public void setNumISBN(Integer numISBN) {
		this.numISBN = numISBN;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Publisher getPublisher() {
		return this.publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<Author> getAuthors() {
		return this.authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<Page> getPages() {
		return this.pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public Page addPage(Page page) {
		getPages().add(page);
		page.setMedia(this);

		return page;
	}

	public Page removePage(Page page) {
		getPages().remove(page);
		page.setMedia(null);

		return page;
	}
	
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}