package br.com.supera.game.store;

import java.util.List;

import com.github.dbunit.rules.DBUnitRule;
import com.github.dbunit.rules.api.configuration.DBUnit;
import com.github.dbunit.rules.api.dataset.DataSet;
import com.github.dbunit.rules.util.EntityManagerProvider;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.dbunit.rules.util.EntityManagerProvider.em;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class ProductDaoExampleTest {

    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("productDS");  

    @Rule
	public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection()); 
	
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml") 
    public void shouldListProducts() {
		try {
			List<Product> products = em(). 
					createQuery("select p from Product p").
					getResultList();
			assertNotNull(products);
			assertEquals(9, products.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
        
    }
}
