package org.vaadin.test.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.vaadin.jpatablegenerator.annotations.MyColumn;
import org.vaadin.jpatablegenerator.annotations.MyEdit;

import com.vaadin.ui.ComboBox;

@Entity
@Table(name = "state", catalog = "mydemo")
public class State implements java.io.Serializable {

	private static final long serialVersionUID = 1061537955774365120L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 11)
	private Integer id;

	@Column(name = "name", nullable = false, length = 45)
	@MyColumn(id = "name")
	@MyEdit(caption = "Name")
	private String name;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
//	private Set<City> cities = new HashSet<City>(0);

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "countryId", nullable = false)
	@MyColumn(id = "country.name")
	@MyEdit(id = "country.id", caption = "Country", itemCaption = "contry.name", componentType = ComboBox.class)
	private Country country;

	public State() {
	}

	public State(String name, Country country) {
		this.country = country;
		this.name = name;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}


}
