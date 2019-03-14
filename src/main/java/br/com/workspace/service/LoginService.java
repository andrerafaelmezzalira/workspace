package br.com.workspace.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.workspace.entity.Person;
import br.com.workspace.exception.IdFoundException;
import br.com.workspace.exception.IdNotFoundException;

/**
 * 
 * Class that performs business rules related to login
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class LoginService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private PersonService personService;

	/**
	 * Validate person. If not exists, create temporarily
	 * 
	 * @param person
	 * @return Person
	 * @throws IdNotFoundException
	 * @throws IdFoundException
	 */
	public Person login(Person person) throws IdNotFoundException, IdFoundException {

		if (person.getName() == null || "".equals(person.getName())) {
			log.log(Level.SEVERE, "name is incorrect ");
			throw new IdNotFoundException("name is incorrect ");
		}
		// get person in bd if exists
		Person personBd = personService.getByName(person.getName());

		if (personBd == null) {
			// create person object temporarily
			log.log(Level.INFO, "create person object temporarily ");
			return personService.persist(person);
		}
		return personBd;
	}

}