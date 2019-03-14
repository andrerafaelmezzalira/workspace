package br.com.workspace.repository;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.workspace.entity.AbstractEntity;

/**
 * Provides access to the entityManager object. Implements methods to: insert,
 * update, delete e select (all e by id)
 * 
 * @author andrerafaelmezzalira
 *
 */
public class AbstractRepository<T extends AbstractEntity<?>> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	protected EntityManager entityManager;

	private Class<T> entityClass;

	/**
	 * Represents the implementing entity class
	 * 
	 * @param entityClass
	 */
	public AbstractRepository(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Executes the sql insert statement
	 * 
	 * @param t
	 * @return entity
	 */
	public T persist(final T t) {
		log.log(Level.INFO, "persist in {0} ", t.getClass().getName());
		entityManager.persist(t);
		entityManager.flush();
		log.log(Level.INFO, "persist id {0} ", t.getId());
		return t;
	}

	/**
	 * Executes the sql update statement
	 * 
	 * @param t
	 * @return entity
	 */
	public T merge(final T t) {
		log.log(Level.INFO, "merge in {0} ", t.getClass().getName());
		entityManager.merge(t);
		entityManager.flush();
		log.log(Level.INFO, "merge id {0} ", t.getId());
		return t;
	}

	/**
	 * Executes the sql statement delete
	 * 
	 * @param t
	 */
	public void remove(final T t) {
		log.log(Level.INFO, "remove in {0} ", t.getClass().getName());
		entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
		entityManager.flush();
		log.log(Level.INFO, "remove id {0} ", t.getId());
	}

	/**
	 * Select from table by id
	 * 
	 * @param id
	 * @return t
	 */
	public T getById(final Serializable id) {
		log.log(Level.INFO, "get by id in {0} and id {1}", new Object[] { entityClass.getName(), id });
		return entityManager.find(entityClass, id);
	}

	/**
	 * Select all from the table
	 * 
	 * @return List<T>
	 */
	public List<T> get() {
		log.log(Level.INFO, "get all in {0} ", entityClass.getClass().getName());
		final CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(entityClass);
		return entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(entityClass))).getResultList();
	}
}
