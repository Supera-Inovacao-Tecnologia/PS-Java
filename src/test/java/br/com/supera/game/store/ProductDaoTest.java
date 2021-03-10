package br.com.supera.game.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
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
import static com.github.dbunit.rules.util.EntityManagerProvider.em;

import br.com.supera.game.db.ProductDao;

public class ProductDaoTest{

	Logger LOGGER;
	
    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("productDS");  

    @Rule
	public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection()); 
	
    @Before
    public void initializeTestClass() {
    	 LOGGER = LoggerFactory.getLogger(this.getClass());
    }
    
    
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml") 
    public void readTest() {
		try {
			LOGGER.info("Testing READ operations");
			
			Product p = new ProductDao(em()).getById(74);
			assertNotNull(p);
			
			Product p2 = new ProductDao(em()).getByField("name", "Call Of Duty Infinite Warfare");
			assertNotNull(p2);
			
			List<Product> pList = new ProductDao(em()).getAll();
			assertNotNull(pList);
			LOGGER.info("Asserting size of the list of Products");
			assertNotEquals(pList.size(), 0);
			
			LOGGER.info("Printing first and last results from the retrieved list of Products");
			LOGGER.info(pList.get(0).toString());
			LOGGER.info(pList.get(pList.size()-1).toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
					
    }
	
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml") 
    public void writeTest() {
	try {	
			LOGGER.info("Testing WRITE operations");
			
			Product p = Product.builder()
					.withName("TestName")
					.withPrice(BigDecimal.valueOf(10))
					.withImage("TestImagePath")
					.withScore((short) 10)
					.build();
			
			ProductDao productDao = new ProductDao(em());
			productDao.persistAutoCommit(p);
			Product p2 = productDao.getByField("name", p.getName());
			LOGGER.info("Asserting that the inserted product and retrived have the same hashcode");
			assertEquals(p.hashCode(), p2.hashCode());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
    }
	
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml") 
    public void updateTest() {
		try {
			LOGGER.info("Testing UPDATE operations");
			
			ProductDao productDao = new ProductDao(em());
			Product p = productDao.getByField("name", "Call Of Duty WWII");
			assertNotNull(p);
			
			LOGGER.info("Updating price of {} to $45", p.toString());
			BigDecimal previousP2Value = p.getPrice();
			p.setPrice(BigDecimal.valueOf(45));
			
			productDao.mergeAutoCommit(p);
			
			Product p2 = productDao.getByField("name", p.getName());
			
			LOGGER.info("Asserting that the inserted product and retrived have the same hashcode");
			assertNotEquals(previousP2Value, p2.getPrice());
			assertEquals(p2.getPrice(), BigDecimal.valueOf(45));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}					
    }
	
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml") 
    public void deleteTest() {
		try {
			List<Product> pList = new ProductDao(em()).getAll();
			assertNotNull(pList);
			int productListInitialSize = pList.size();
			
			LOGGER.info("Testing UPDATE operations");
			
			ProductDao productDao = new ProductDao(em());
			Product p = productDao.getByField("name", "Call Of Duty WWII");
			assertNotNull(p);
			
			LOGGER.info("Deleting {} ", p.toString());
			productDao.remove(p);
			
			LOGGER.info("Asserting that the deleted product is not in the database anymore");
			Product p2 = productDao.getByField("name", "Call Of Duty WWII");
			assertNull(p2);
	
			LOGGER.info("Asserting that the size of the list of product has decreased");
			List<Product> pList2 = new ProductDao(em()).getAll();
			int finalSize = pList2.size();
			assertNotNull(pList2);
			assertEquals(productListInitialSize-1, finalSize);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}					
    }
}
