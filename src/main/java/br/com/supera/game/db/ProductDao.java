package br.com.supera.game.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.supera.game.store.Product;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Class that will perform database operations for the User object This class is
 * a DAO Pattern implementation This class perform database operations through
 * JPA This class follows the Singleton Pattern
 */
public class ProductDao {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/*mechanism to feed the dao with the EntityManger from the DBUnit Rules*/
	private EntityManager entityManager;
	
	public ProductDao(EntityManager em) {
		this.entityManager = em;
	}

	public synchronized Product getById(final int id) {
		
		LOGGER.debug("Finding product with id equals to {}", id);
		
		Product p = null;
		try {	
			
			 p = entityManager.find(Product.class, id);
			 
		}catch (Exception e){
			
			LOGGER.debug("There was an error fetching the results of product by field id {} ", id);
			e.printStackTrace();
			throw e;
			
		}finally {
			
//			entityManager.close();	
//			LOGGER.debug("Entity closed");
		}
		
		return p;
	}


	public synchronized Product getByField(String fieldName, String fieldValue) {		
		
		LOGGER.debug("Finding product by field {} equals to {}", fieldName, fieldValue);

		try {
			
			Product product = entityManager
					.createQuery("select user from Product as user where user." + fieldName + " = \'" + fieldValue + "\'", Product.class)
					.getSingleResult();
			return product;
		
		} catch (NoResultException nre) {
			
			LOGGER.debug("Couldn't find any product by field {} equals to {}", fieldName, fieldValue);
			//it is good to use Optional API
			return null;
			
		} catch (Exception ex) {
		
			LOGGER.debug("There was an error fetching the results of product by field {} equals to {}", fieldName, fieldValue);
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
			
		}finally {
			
//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAll() {
		
		LOGGER.debug("Finding all results for Product");
		
		try {
			
			return entityManager.createQuery("select p from Product p").getResultList();
			
		}catch (NoResultException nre) {
				
			LOGGER.debug("Couldn't find any product");
			//it is good to use Optional API
			return null;
					
		}catch(Exception e){
			
			LOGGER.debug("There was an error fetching the results ");
			throw e;
			
		}
		finally {
			
//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}

	public synchronized void persistAutoCommit(Product product) throws RuntimeException { 
		
		LOGGER.debug("Persisting Product {}", product.toString());
		
		try {
			
			entityManager.getTransaction().begin();
			entityManager.persist(product);
			entityManager.getTransaction().commit();
			
		} catch (Exception ex) {
			
			LOGGER.debug("There was an error persiting {}", product.toString());
			entityManager.getTransaction().rollback();
			ex.printStackTrace();
			throw ex;
			
		}
		finally {
			
//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}

	public synchronized void mergeAutoCommit(Product product) {
		
		LOGGER.debug("Merging "+ product.toString());

		
		try {
			
			entityManager.getTransaction().begin();
			entityManager.merge(product);
			entityManager.getTransaction().commit();
			
		} catch (Exception ex) {
			
			LOGGER.debug("There was an error merging {}", product.toString());
			entityManager.getTransaction().rollback();
			ex.printStackTrace();
			throw ex;
			
		}
		finally {
			
//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}

	public synchronized void remove(Product product) {
		
		LOGGER.debug("Removing "+ product.toString());
		
		try {
			
			entityManager.getTransaction().begin();
			entityManager.remove(product);
			entityManager.getTransaction().commit();
			
		} catch (Exception ex) {
			
			LOGGER.debug("There was an error removing {}", product.toString());
			entityManager.getTransaction().rollback();
			ex.printStackTrace();
			throw ex;
			
		}
		finally {
			
//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}

	public synchronized void removeById(final int id) {
		
		Product product = getById(id);
		
		try {
			
			entityManager.getTransaction().begin();
			entityManager.remove(product);
			entityManager.getTransaction().commit();
			
		} catch (Exception ex) {
			
			LOGGER.debug("There was an error removing {}", product.toString());
			entityManager.getTransaction().rollback();
			ex.printStackTrace();
			throw ex;
			
		}
		finally {
			
//			entityManager.close();
//			LOGGER.debug("Entity closed");
		}
	}
}