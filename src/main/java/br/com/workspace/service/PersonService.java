package br.com.workspace.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.workspace.entity.Person;
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
	 * Validate person. If not exists, create temporarily
	 * 
	 * @param person
	 * @return Person
	 */
	public Person login(Person person) {

		// get person in bd if exists
		Person personBd = null;
		List<Person> persons = repository.getByName(person.getName().trim().toUpperCase());
		if (!persons.isEmpty()) {
			personBd = persons.get(0);
		}

		if (personBd == null) {
			// create person object temporarily
			log.info("create person object temporarily");
			return repository.persist(person);
		}
		return personBd;
	}
}