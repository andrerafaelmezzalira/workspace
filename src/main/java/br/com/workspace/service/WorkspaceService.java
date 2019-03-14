package br.com.workspace.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.workspace.entity.Person;
import br.com.workspace.entity.Workspace;
import br.com.workspace.exception.ForeignKeyException;
import br.com.workspace.exception.IdFoundException;
import br.com.workspace.exception.IdNotFoundException;
import br.com.workspace.repository.WorkspaceRepository;

/**
 * 
 * Class that performs business rules related to Workspace table
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class WorkspaceService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	public static final String PERSON_NOT_FOUND = "person not found";

	@Inject
	private WorkspaceRepository repository;

	@Inject
	private PersonService personService;

	/**
	 * Get workspace's by person
	 * 
	 * @param idPerson
	 * @return List<Workspace>
	 */
	public List<Workspace> getByPerson(Integer idPerson) {
		return repository.getByPerson(idPerson);
	}

	/**
	 * Update Workspace
	 * 
	 * @param workspace
	 * @return List<Workspace>
	 * @throws IdNotFoundException
	 * @throws ForeignKeyException
	 */
	public List<Workspace> merge(Workspace workspace) throws IdNotFoundException, ForeignKeyException {

		if (workspace.getPerson() == null || workspace.getPerson().getId() == null) {
			log.log(Level.SEVERE, PERSON_NOT_FOUND);
			throw new ForeignKeyException(PERSON_NOT_FOUND);
		}

		Workspace workspaceBd = repository.getById(workspace.getId());

		if (workspaceBd == null) {
			log.log(Level.SEVERE, "{0} not found  ", workspace.getId());
			throw new IdNotFoundException(workspace.getId() + " not found");
		}

		if (!workspace.getPerson().getId().equals(workspaceBd.getPerson().getId())) {
			log.log(Level.SEVERE, "person incorrect input {0} bd {1}  ",
					new Object[] { workspace.getId(), workspaceBd.getId() });
			throw new ForeignKeyException("person incorrect");
		}
		workspace.setPerson(workspaceBd.getPerson());
		workspace = repository.merge(workspace);
		return getByPerson(workspace.getPerson().getId());
	}

	/**
	 * Remove workspace
	 * 
	 * @param id
	 * @return List<Workspace>
	 * @throws IdNotFoundException
	 */
	public List<Workspace> remove(Integer id) throws IdNotFoundException {
		Workspace workspace = repository.getById(id);
		if (workspace == null) {
			log.log(Level.SEVERE, "{0} not found  ", id);
			throw new IdNotFoundException(id + " not found");
		}
		int idPerson = workspace.getPerson().getId();
		repository.remove(workspace);
		return getByPerson(idPerson);
	}

	/**
	 * Persist a workspace
	 * 
	 * @param workspace
	 * @return List<Workspace>
	 * @throws ForeignKeyException
	 * @throws IdFoundException
	 */
	public List<Workspace> persist(Workspace workspace) throws ForeignKeyException, IdFoundException {

		if (workspace.getId() != null) {
			log.log(Level.SEVERE, "request incorrect {0} ", workspace.getId());
			throw new IdFoundException("request incorrect");
		}

		if (workspace.getPerson() == null || workspace.getPerson().getId() == null) {
			log.log(Level.SEVERE, PERSON_NOT_FOUND);
			throw new ForeignKeyException(PERSON_NOT_FOUND);
		}

		Person person = personService.getById(workspace.getPerson().getId());

		if (person == null) {
			log.log(Level.SEVERE, PERSON_NOT_FOUND);
			throw new ForeignKeyException("person incorrect");
		}

		workspace.setPerson(person);
		workspace = repository.persist(workspace);
		return getByPerson(workspace.getPerson().getId());
	}

	/**
	 * Get all workspace's
	 * 
	 * @return List<Workspace>
	 */
	public List<Workspace> get() {
		return repository.get();
	}

}