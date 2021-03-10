package br.com.supera.game.service;

import static com.github.dbunit.rules.util.EntityManagerProvider.em;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dbunit.rules.DBUnitRule;
import com.github.dbunit.rules.api.configuration.DBUnit;
import com.github.dbunit.rules.api.dataset.DataSet;
import com.github.dbunit.rules.util.EntityManagerProvider;

import br.com.supera.game.db.ProductDao;
import br.com.supera.game.store.Product;

/*The repeated code could be replaced by using Java Reflections in case of large amount of data*/
public class ProductServiceTest {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private List<Product> pList;
	
    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("productDS");  

    @Rule
	public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection()); 
	
    @Before
    public void initializeTestClass() {
    	//fast when using in memory database
    	pList = new ProductDao(em()).getAll(); 
    	assertNotNull(pList);
    }
    
    @Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml")
    public void getAllProductsOrderedByNameTest() {
    	
    	LOGGER.debug("Initiating test of ordering by name using getAllProductsOrdered method");
    
		ProductService ps = new ProductService();
		
		List<Product> pListOrdered  = ps.getAllProductsOrdered("name", pList);
			
		int indexPBefore;
		for(Product pCurrent : pListOrdered) {
			//reusing variable
			indexPBefore = pListOrdered.indexOf(pCurrent) - 1;
			
			if(indexPBefore < 0) continue;
			Product pBefore = pListOrdered.get(indexPBefore);
			
			LOGGER.debug(String.valueOf(pBefore.getName().compareTo(pCurrent.getName())));
			//Asserting that the products are in natural order 
			assertTrue((pBefore.getName().compareTo(pCurrent.getName())) <= 0);
		}
		
		
    }
    
    @Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml")
    public void getAllProductsOrderedByPriceTest() {
    	
    	LOGGER.debug("Initiating test of ordering by price using getAllProductsOrdered method");
    
		ProductService ps = new ProductService();
		
		List<Product> pListOrdered  = ps.getAllProductsOrdered("price", pList);
		
		int indexPBefore;
		for(Product pCurrent : pListOrdered) {
			//reusing variable
			indexPBefore = pListOrdered.indexOf(pCurrent) - 1;
			
			if(indexPBefore < 0) continue;
			Product pBefore = pListOrdered.get(indexPBefore);
			
			LOGGER.debug(String.valueOf(pBefore.getPrice().compareTo(pCurrent.getPrice())));
			//Asserting that the products are in natural order 
			assertTrue((pBefore.getPrice().compareTo(pCurrent.getPrice())) <= 0);
		}
    }
    
    @Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml")
    public void getAllProductsOrderedByScoreTest() {
    	
    	LOGGER.debug("Initiating test of ordering by score using getAllProductsOrdered method");
    
		ProductService ps = new ProductService();
		
		List<Product> pListOrdered  = ps.getAllProductsOrdered("score", pList);
		
		int indexPBefore;
		for(Product pCurrent : pListOrdered) {
			//reusing variable
			indexPBefore = pListOrdered.indexOf(pCurrent) - 1;
			
			if(indexPBefore < 0) continue;
			Product pBefore = pListOrdered.get(indexPBefore);
			
			LOGGER.debug(String.valueOf(Integer.valueOf(pBefore.getScore()).compareTo(Integer.valueOf(pCurrent.getScore()))));
			//Asserting that the products are in natural order 
			assertTrue((Integer.valueOf(pBefore.getScore()).compareTo(Integer.valueOf(pCurrent.getScore()))) <= 0);
		}
    }
    
    @Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml")
    public void getAllProductsOrderedByIdTest() {
    	
    	LOGGER.debug("Initiating test of ordering by score using getAllProductsOrdered method");
    
		ProductService ps = new ProductService();
		
		List<Product> pListOrdered  = ps.getAllProductsOrdered("id", pList);
		
		int indexPBefore;
		for(Product pCurrent : pListOrdered) {
			//reusing variable
			indexPBefore = pListOrdered.indexOf(pCurrent) - 1;
			
			if(indexPBefore < 0) continue;
			Product pBefore = pListOrdered.get(indexPBefore);
			
			LOGGER.debug(String.valueOf(pBefore.getId().compareTo(pCurrent.getId())));
			//Asserting that the products are in natural order 
			assertTrue((pBefore.getId()).compareTo(pCurrent.getId()) <= 0);
		}
    }
	
}
