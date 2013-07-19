package org.vaadin.mytablegeneratordemo.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.vaadin.mytablegenerator.annotations.MyEdit;
import org.vaadin.mytablegenerator.annotations.MyTable;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "country", catalog = "mydemo", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@MyTable(caption = "Countries", popupCaption = "Country", height = 300)
public class Country implements java.io.Serializable {

	private static final long serialVersionUID = -9109914244009012874L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@NotNull
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@NotNull
	@Size(min=2, max=10, message = "SIZEEJAS:KJ")
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

	@Column(name = "year", length = 4)
	@MyEdit(caption = "Year")
	private Integer year;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "country")
	private Set<State> states = new HashSet<State>(0);

	public Country() {
	}

	public Country(String name, Integer population, Date lastUpdate, Integer year) {
		this.name = name;
		this.population = population;
		this.lastUpdate = lastUpdate;
		this.year = year;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



}
