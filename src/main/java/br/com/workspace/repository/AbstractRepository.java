package br.com.workspace.repository;

import java.io.Serializable;
import java.util.List;
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
	 * Represents the Implementing Entity Class
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
		log.info("persist in " + t.getClass().getName());
		entityManager.persist(t);
		entityManager.flush();
		log.info("generate id \n" + t.getId());
		return t;
	}

	/**
	 * Executes the sql update statement
	 * 
	 * @param t
	 * @return entity
	 */
	public T merge(final T t) {
		log.info("update in " + t.getClass().getName());
		entityManager.merge(t);
		entityManager.flush();
		return t;
	}

	/**
	 * Executes the sql statement delete
	 * 
	 * @param t
	 */
	public void remove(final T t) {
		log.info("delete in " + t.getClass().getName());
		entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
		entityManager.flush();
	}

	/**
	 * Select from table by id
	 * 
	 * @param id
	 * @return t
	 */
	public T getById(final Serializable id) {
		log.info("get by id in " + entityClass.getName() + " and id " + id);
		return entityManager.find(entityClass, id);
	}

	/**
	 * Select from all table
	 * 
	 * @return List<T>
	 */
	public List<T> get() {
		log.info("get in " + entityClass.getName());
		final CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(entityClass);
		return entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(entityClass))).getResultList();
	}
}
