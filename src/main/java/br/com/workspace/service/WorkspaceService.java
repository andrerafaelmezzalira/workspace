package br.com.workspace.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.workspace.entity.Workspace;
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

	@Inject
	private WorkspaceRepository repository;

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
	 */
	public List<Workspace> merge(Workspace workspace) {
		Workspace workspaceBd = repository.getById(workspace.getId());
		workspace.setPerson(workspaceBd.getPerson());
		workspace = repository.merge(workspace);
		return getByPerson(workspace.getPerson().getId());
	}

	/**
	 * Remove workspace
	 * 
	 * @param id
	 * @return List<Workspace>
	 */
	public List<Workspace> remove(Integer id) {
		Workspace workspace = repository.getById(id);
		int idPerson = workspace.getPerson().getId();
		repository.remove(workspace);
		return getByPerson(idPerson);
	}

	/**
	 * Persist a workspace
	 * 
	 * @param workspace
	 * @return List<Workspace>
	 */
	public List<Workspace> persist(Workspace workspace) {
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