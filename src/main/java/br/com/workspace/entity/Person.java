package br.com.workspace.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Sets the person table
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = Person.GET_BY_NAME, query = "select a from Person a where upper(a.name) = :name") })
public class Person implements AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;

	/**
	 * Constant representing sql to get person by name
	 */
	public static final String GET_BY_NAME = "Person.getByName";

	public Person() {
		// do not implement
	}

	/**
	 * 
	 * Define the id of the person
	 * 
	 * @param id
	 */
	public Person(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
