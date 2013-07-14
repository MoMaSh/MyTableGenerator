package org.vaadin.mytablegeneratordemo.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "state", catalog = "mydemo")
public class State implements java.io.Serializable {

	private static final long serialVersionUID = 1061537955774365120L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 45)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "countryId", nullable = false)
	private Country country;
	
	@Column(name = "name", nullable = false, length = 45)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
	private Set<City> cities = new HashSet<City>(0);

	public State() {
	}

	public State(String id, Country country, String name) {
		this.id = id;
		this.country = country;
		this.name = name;
	}

	public State(String id, Country country, String name, Set<City> cities) {
		this.id = id;
		this.country = country;
		this.name = name;
		this.cities = cities;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<City> getCities() {
		return this.cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

}
