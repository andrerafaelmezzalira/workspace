package br.com.workspace.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.workspace.entity.Person;
import br.com.workspace.exception.IdFoundException;
import br.com.workspace.repository.PersonRepository;

/**
 * 
 * Class that performs business rules related to Person table
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class PersonService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private PersonRepository repository;

	/**
	 * Return Person by id
	 * 
	 * @param id
	 * @return Person
	 */
	public Person getById(Integer id) {
		return repository.getById(id);
	}

	/**
	 * Return Person by name
	 * 
	 * @param name
	 * @return Person
	 */
	public Person getByName(String name) {
		List<Person> persons = repository.getByName(name.trim().toUpperCase());
		if (!persons.isEmpty()) {
			return persons.get(0);
		}
		return null;
	}

	/**
	 * Persist a person
	 * 
	 * @param person
	 * @return Person
	 * @throws IdFoundException
	 */
	public Person persist(Person person) throws IdFoundException {
		if (person.getId() != null) {
			log.log(Level.SEVERE, "request incorrect persist {0} ", person.getId());
			throw new IdFoundException("request incorrect");
		}
		return repository.persist(person);
	}
}