package org.vaadin.mytablegeneratordemo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "city", catalog = "mydemo", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class City implements java.io.Serializable {

	private static final long serialVersionUID = 3411679817638089139L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stateId", nullable = false)
	private State state;

	@Column(name = "name", unique = true, length = 45)
	private String name;

	public City() {
	}

	public City(State state) {
		this.state = state;
	}

	public City(State state, String name) {
		this.state = state;
		this.name = name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
