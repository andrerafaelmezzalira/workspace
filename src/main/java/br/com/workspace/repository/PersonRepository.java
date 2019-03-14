package br.com.workspace.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.workspace.entity.Person;

/**
 * Represents access to person table
 * 
 * @author andrerafaelmezzalira
 *
 */
public class PersonRepository extends AbstractRepository<Person> {

	/**
	 * Set object Person in repository
	 */
	public PersonRepository() {
		super(Person.class);
	}

	/**
	 * Returns a list of people by name
	 * 
	 * @param name
	 * @return List<Person>
	 */
	public List<Person> getByName(String name) {
		TypedQuery<Person> query = entityManager.createNamedQuery(Person.GET_BY_NAME, Person.class);
		query.setParameter("name", name);
		return query.getResultList();
	}
}