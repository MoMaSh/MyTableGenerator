package org.vaadin.mytablegeneratordemo.entities;

// Generated 12.07.2013 15:41:23 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * State generated by hbm2java
 */
@Entity
@Table(name = "state", catalog = "mydemo")
public class State implements java.io.Serializable {

	private Integer id;
	private String name;
	private int countryId;

	public State() {
	}

	public State(int countryId) {
		this.countryId = countryId;
	}

	public State(String name, int countryId) {
		this.name = name;
		this.countryId = countryId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "country_id", nullable = false)
	public int getCountryId() {
		return this.countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

}