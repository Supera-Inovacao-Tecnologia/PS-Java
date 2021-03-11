package br.com.supera.game.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.supera.game.model.User;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Class that will perform database operations for the User object This class is
 * a DAO Pattern implementation This class perform database operations through
 * JPA This class follows the Singleton Pattern
 */
public class UserDao {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/* mechanism to feed the dao with the EntityManger from the DBUnit Rules */
	private EntityManager entityManager;

	public UserDao(EntityManager em) {
		this.entityManager = em;
	}

	public synchronized User getById(final int id) {

		LOGGER.debug("Finding User with id equals to {}", id);

		User p = null;
		try {

			p = entityManager.find(User.class, id);

		} catch (Exception e) {

			LOGGER.debug("There was an error fetching the results of User by field id {} ", id);
			e.printStackTrace();
			throw e;

		} finally {

//			entityManager.close();	
//			LOGGER.debug("Entity closed");
		}

		return p;
	}

	public synchronized User getByField(String fieldName, String fieldValue) {

		LOGGER.debug("Finding User by field {} equals to {}", fieldName, fieldValue);

		try {

			User User = entityManager
					.createQuery("select user from User as user where user." + fieldName + " = \'" + fieldValue + "\'",
							User.class)
					.getSingleResult();
			return User;

		} catch (NoResultException nre) {

			LOGGER.debug("Couldn't find any User by field {} equals to {}", fieldName, fieldValue);
			// it is good to use Optional API
			return null;

		} catch (Exception ex) {

			LOGGER.debug("There was an error fetching the results of User by field {} equals to {}", fieldName,
					fieldValue);
			ex.printStackTrace();
			entityManager.getTransaction().rollback();

		} finally {

//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {

		LOGGER.debug("Finding all results for User");

		try {

			return entityManager.createQuery("select p from User p").getResultList();

		} catch (NoResultException nre) {

			LOGGER.debug("Couldn't find any User");
			// it is good to use Optional API
			return null;

		} catch (Exception e) {

			LOGGER.debug("There was an error fetching the results ");
			throw e;

		} finally {

//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}

	public synchronized void persistAutoCommit(User User) throws RuntimeException {

		LOGGER.debug("Persisting User {}", User.toString());

		try {

			entityManager.getTransaction().begin();
			entityManager.persist(User);
			entityManager.getTransaction().commit();

		} catch (Exception ex) {

			LOGGER.debug("There was an error persiting {}", User.toString());
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
			throw ex;

		} finally {

//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}

	public synchronized void mergeAutoCommit(User User) {

		LOGGER.debug("Merging " + User.toString());

		try {

			entityManager.getTransaction().begin();
			entityManager.merge(User);
			entityManager.getTransaction().commit();

		} catch (Exception ex) {

			LOGGER.debug("There was an error merging {}", User.toString());
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
			throw ex;

		} finally {

//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}

	public synchronized void remove(User User) {

		LOGGER.debug("Removing " + User.toString());

		try {

			entityManager.getTransaction().begin();
			entityManager.remove(User);
			entityManager.getTransaction().commit();

		} catch (Exception ex) {

			LOGGER.debug("There was an error removing {}", User.toString());
			entityManager.getTransaction().rollback();
			ex.printStackTrace();
			throw ex;

		} finally {

//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}

	public synchronized void removeById(final int id) {

		User User = getById(id);

		try {

			entityManager.getTransaction().begin();
			entityManager.remove(User);
			entityManager.getTransaction().commit();

		} catch (Exception ex) {

			LOGGER.debug("There was an error removing {}", User.toString());
			entityManager.getTransaction().rollback();
			ex.printStackTrace();
			throw ex;

		} finally {

//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}
}