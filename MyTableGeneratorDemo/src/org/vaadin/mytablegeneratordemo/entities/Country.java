package org.vaadin.mytablegeneratordemo.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.vaadin.mytablegenerator.annotations.MyEdit;
import org.vaadin.mytablegenerator.annotations.MyTable;

@Entity
@Table(name = "country", catalog = "mydemo", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@MyTable(caption = "Countries", popupCaption = "Country", height = 300)
public class Country implements java.io.Serializable {

	private static final long serialVersionUID = -9109914244009012874L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", unique = true, nullable = false, length = 100)
	@MyEdit(caption = "Name")
	private String name;
	
	@Column(name = "population")
	@MyEdit(caption = "Population")
	private Integer population;

	@Temporal(TemporalType.DATE)
	@Column(name = "lastUpdate", length = 10)
	@MyEdit(caption = "Last Update")
	private Date lastUpdate;
	
	@Column(name = "year")
	@MyEdit(caption = "Year")
	private Integer year;

	
	public Country() {
	}

	public Country(String name, Integer population, Date lastUpdate, Integer year) {
		this.name = name;
		this.population = population;
		this.lastUpdate = lastUpdate;
		this.year = year;
	}

}
