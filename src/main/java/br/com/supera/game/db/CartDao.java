package br.com.supera.game.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.supera.game.store.Cart;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Class that will perform database operations for the Cart object This class is
 * a DAO Pattern implementation This class perform database operations through
 * JPA This class follows the Singleton Pattern
 */
public class CartDao {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/* mechanism to feed the dao with the EntityManger from the DBUnit Rules */
	private EntityManager entityManager;

	public CartDao(EntityManager em) {
		this.entityManager = em;
	}

	public synchronized Cart getById(final int id) {

		LOGGER.debug("Finding Cart with id equals to {}", id);

		Cart p = null;
		try {

			p = entityManager.find(Cart.class, id);

		} catch (Exception e) {

			LOGGER.debug("There was an error fetching the results of Cart by field id {} ", id);
			e.printStackTrace();
			throw e;

		} finally {

//			entityManager.close();	
//			LOGGER.debug("Entity closed");
		}

		return p;
	}

	public synchronized Cart getByField(String fieldName, String fieldValue) {

		LOGGER.debug("Finding Cart by field {} equals to {}", fieldName, fieldValue);

		try {

			Cart Cart = entityManager
					.createQuery("select cart from Cart as cart where cart." + fieldName + " = \'" + fieldValue + "\'",
							Cart.class)
					.getSingleResult();
			return Cart;

		} catch (NoResultException nre) {

			LOGGER.debug("Couldn't find any Cart by field {} equals to {}", fieldName, fieldValue);
			// it is good to use Optional API
			return null;

		} catch (Exception ex) {

			LOGGER.debug("There was an error fetching the results of Cart by field {} equals to {}", fieldName,
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
	public List<Cart> getAll() {

		LOGGER.debug("Finding all results for Cart");

		try {

			return entityManager.createQuery("select p from Cart p").getResultList();

		} catch (NoResultException nre) {

			LOGGER.debug("Couldn't find any Cart");
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

	public synchronized void persistAutoCommit(Cart Cart) throws RuntimeException {

		LOGGER.debug("Persisting Cart {}", Cart.toString());

		try {

			entityManager.getTransaction().begin();
			entityManager.persist(Cart);
			entityManager.getTransaction().commit();

		} catch (Exception ex) {

			LOGGER.debug("There was an error persiting {}", Cart.toString());
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
			throw ex;

		} finally {

//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}

	public synchronized void mergeAutoCommit(Cart Cart) {

		LOGGER.debug("Merging " + Cart.toString());

		try {

			entityManager.getTransaction().begin();
			entityManager.merge(Cart);
			entityManager.getTransaction().commit();

		} catch (Exception ex) {

			LOGGER.debug("There was an error merging {}", Cart.toString());
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
			throw ex;

		} finally {

//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}

	public synchronized void remove(Cart Cart) {

		LOGGER.debug("Removing " + Cart.toString());

		try {

			entityManager.getTransaction().begin();
			entityManager.remove(Cart);
			entityManager.getTransaction().commit();

		} catch (Exception ex) {

			LOGGER.debug("There was an error removing {}", Cart.toString());
			entityManager.getTransaction().rollback();
			ex.printStackTrace();
			throw ex;

		} finally {

//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}

	public synchronized void removeById(final int id) {

		Cart Cart = getById(id);

		try {

			entityManager.getTransaction().begin();
			entityManager.remove(Cart);
			entityManager.getTransaction().commit();

		} catch (Exception ex) {

			LOGGER.debug("There was an error removing {}", Cart.toString());
			entityManager.getTransaction().rollback();
			ex.printStackTrace();
			throw ex;

		} finally {

//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}
}