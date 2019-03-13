package br.com.workspace.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.workspace.entity.Workspace;

/**
 * Represents access to workspace table
 * 
 * @author andrerafaelmezzalira
 *
 */
public class WorkspaceRepository extends AbstractRepository<Workspace> {

	/**
	 * Set object workspace in repository
	 */
	public WorkspaceRepository() {
		super(Workspace.class);
	}

	/**
	 * Get a list the workspace's by person
	 * 
	 * @param idPerson
	 * @return List<Workspace>
	 */
	public List<Workspace> getByPerson(Integer idPerson) {
		TypedQuery<Workspace> query = entityManager.createNamedQuery(Workspace.GET_BY_PERSON, Workspace.class);
		query.setParameter("idPerson", idPerson);
		return query.getResultList();
	}
}