package br.com.workspace.producer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Produces an entityManager instance for each request
 * 
 * @author andrerafaelmezzalira
 *
 */
public class EntityManagerProducer {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@PersistenceContext
	private EntityManager entityManager;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		log.log(Level.INFO, "new instance entityManager request scoped");
		return entityManager;
	}
}