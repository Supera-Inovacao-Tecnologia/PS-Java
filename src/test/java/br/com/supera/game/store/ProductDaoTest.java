package br.com.supera.game.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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
		
		LOGGER.info("Testing READ operations");
		
		Product p = new ProductDao(emProvider).getById(74);
		assertNotNull(p);
		
		Product p2 = new ProductDao(emProvider).getByField("name", "Call Of Duty Infinite Warfare");
		assertNotNull(p2);
		
		List<Product> pList = new ProductDao(emProvider).findAll();
		assertNotNull(pList);
		LOGGER.info("Asserting size of the list of Products");
		assertNotEquals(pList.size(), 0);
		
		LOGGER.info("Printing first and last results from the retrieved list of Products");
		LOGGER.info(pList.get(0).toString());
		LOGGER.info(pList.get(pList.size()-1).toString());
					
    }
	
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml") 
    public void writeTest() {
		
		LOGGER.info("Testing WRITE operations");
		
		Product p = Product.builder()
				.withName("TestName")
				.withPrice(BigDecimal.valueOf(10))
				.withImage("TestImagePath")
				.withScore((short) 10)
				.build();
		
		ProductDao productDao = new ProductDao(emProvider);
		productDao.persistAutoCommit(p);
		Product p2 = productDao.getByField("name", p.getName());
		LOGGER.info("Asserting that the inserted product and retrived have the same hashcode");
		assertEquals(p.hashCode(), p2.hashCode());
							
    }
	
	
}
