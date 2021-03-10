package br.com.supera.game.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JPAEntityManager {

	private static JPAEntityManager jpaEMInstance;
	private final Logger LOGGER;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DatabaseInformation.persistenceUnitName);
	private Set<EntityManager> emSet = new HashSet<EntityManager>();

	public static JPAEntityManager getInstance() {
		if (jpaEMInstance == null) {
			jpaEMInstance = new JPAEntityManager();
		}
		return jpaEMInstance;
	}
	private JPAEntityManager() {
		LOGGER = LoggerFactory.getLogger(this.getClass());
	}
	

	public synchronized final EntityManager getEntityManager() {
		LOGGER.debug("Creating new EntityManager");
		EntityManager em = emf.createEntityManager();
		this.emSet.add(em);
		return em;
	}

	public synchronized final void closeEntity(EntityManager em) {
		em.close();
		this.emSet.remove(em);
	}

	public Set<EntityManager> getEmSet() {
		return emSet;
	}

}
