package br.com.supera.game.store;

import static com.github.dbunit.rules.util.EntityManagerProvider.em;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

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

import br.com.supera.game.db.CartDao;
import br.com.supera.game.db.ProductDao;

public class CartDaoTest{

	Logger LOGGER;
	 
	//put products in initial state
	 List<Product> productList;
	 Product p1;
	 Product p2;
	
    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("productDS");  

    @Rule
	public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection()); 
	
    @Before
    public void  initialization() {
    	LOGGER = LoggerFactory.getLogger(this.getClass());
    	ProductDao pDao = new ProductDao(em());
    	this.productList = pDao.getAll();
    	
    	p1 = productList.get(0);
    	p2 = productList.get(1);
    }
    
 
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml") 
    public void writeTest() {
	try {	
			LOGGER.info("Testing ADD PRODUCTS operations");
			
			Cart c = new Cart();
			
			LOGGER.info("Asserting the right initial state for Cart");
			assertNotNull(c);
			assertNotNull(c.getResourceRepresentation());
			assertEquals(0, c.getProductIdQuantMap().size());
			
			c.addProduct(p1);
			
			assertNotNull(c.getResourceRepresentation());
			assertEquals(1, c.getProductIdQuantMap().size());
			assertNotNull(c.getProductIdQuantMap().get(p1.getId()));
			
			c.addProduct(p2);
			
			assertNotNull(c.getResourceRepresentation());
			assertEquals(2, c.getProductIdQuantMap().size());
			assertNotNull(c.getProductIdQuantMap().get(p2.getId()));
			
			LOGGER.info("Testing REMOVE operations");
			
			c.removeProduct(p1);
			
			assertEquals(1, c.getProductIdQuantMap().size());
			assertNull(c.getProductIdQuantMap().get(p1.getId()));
			
			c.removeProduct(p2);
			
			assertEquals(0, c.getProductIdQuantMap().size());
			assertNull(c.getProductIdQuantMap().get(p2.getId()));
			
			CartDao cartDao = new CartDao(em());
			cartDao.persistAutoCommit(c);
			
			Cart c2 = cartDao.getByField("id", String.valueOf(c.getId()));
			
			LOGGER.info("Asserting that the inserted product and retrived have the same hashcode");
			assertEquals(c.hashCode(), c2.hashCode());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
    }
	
}
