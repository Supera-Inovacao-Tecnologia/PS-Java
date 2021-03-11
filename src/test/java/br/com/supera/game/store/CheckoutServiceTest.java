package br.com.supera.game.store;

import static com.github.dbunit.rules.util.EntityManagerProvider.em;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

import br.com.supera.game.db.ProductDao;
import br.com.supera.game.service.CheckoutService;

public class CheckoutServiceTest{

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	BigDecimal noShippingPriceThreshold = BigDecimal.valueOf(250);// TODO get from .properties file
	
	private Cart cart;
	 
	//put products in initial state
	 List<Product> productList;
	
    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("productDS");  

    @Rule
	public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection()); 
	
    @Before
    public void  initialization() {
    	
    	cart = new Cart();
    	
    }
    
 
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml") 
    public void freeShipping() {
		
		try {	
			
			//price: 197.88
			Product p1 = new ProductDao(em()).getByField("price", String.valueOf(197.88));
	    	
			int numberOfAddedSameProduct = 3;
			
			BigDecimal totalProductPrice = BigDecimal.valueOf(0);
	    	
			//Adding the same product the cart
			for(int i = 0 ; i < numberOfAddedSameProduct ; i++) {
				cart.addProduct(p1);	    		
				totalProductPrice = totalProductPrice.add(p1.getPrice());
			}
	    	
	    	LOGGER.info(totalProductPrice.toPlainString());
	    	LOGGER.info(noShippingPriceThreshold.toPlainString());
	    	
	    	assertTrue(totalProductPrice.compareTo(noShippingPriceThreshold) > 0);	    	
	    	
	    	assertEquals(Integer.valueOf(numberOfAddedSameProduct), cart.getProductIdQuantMap().get(p1.getId()));
			
			CheckoutService checkoutService = new CheckoutService();
			
			Checkout checkout = checkoutService.getCheckoutFromCart(cart);
			
			assertTrue(checkout.getShippingPriceStatus() == false);
			
			LOGGER.info("Subtotal: " + checkout.getSubtotalPrice().toPlainString());
			
			assertTrue(checkout.getSubtotalPrice().compareTo(noShippingPriceThreshold) > 0);
			
			//asserting shipping price is not included
			LOGGER.info("TEST");
			LOGGER.info(String.valueOf(checkout.getTotalPrice().subtract(checkout.getSubtotalPrice())));
			assertTrue(checkout.getTotalPrice().subtract(checkout.getSubtotalPrice()).compareTo(BigDecimal.valueOf(0)) == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
    }
	
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml") 
    public void noFreeShipping() {
		
		try {	
			
			//price: 197.88
			Product p1 = new ProductDao(em()).getByField("price", String.valueOf(197.88));

			int numberOfAddedSameProduct = 1;
			
			BigDecimal totalProductPrice = BigDecimal.valueOf(0);
	    	
			//Adding the same product the cart
			for(int i = 0 ; i < numberOfAddedSameProduct ; i++) {
				cart.addProduct(p1);	    		
				totalProductPrice = totalProductPrice.add(p1.getPrice());
			}
	    	
	    	LOGGER.info(totalProductPrice.toPlainString());
	    	LOGGER.info(noShippingPriceThreshold.toPlainString());	    	
	    	
	    	//Asserting the price of all the product is less the required to free shipping
	    	assertTrue(totalProductPrice.compareTo(noShippingPriceThreshold) <= 0);
	    	
			CheckoutService checkoutService = new CheckoutService();
			
			Checkout checkout = checkoutService.getCheckoutFromCart(cart);
			
			//Asserting that the shipping price must be paid
			assertTrue(checkout.getShippingPriceStatus() == true);
			
			LOGGER.info("Subtotal: " + checkout.getSubtotalPrice().toPlainString() + " is less the " + this.noShippingPriceThreshold);
			
			//Sub total is less than 250
			assertTrue(checkout.getSubtotalPrice().compareTo(noShippingPriceThreshold) < 0);
			
			
			//asserting shipping price IS included
			assertTrue(checkout.getTotalPrice().subtract(checkout.getSubtotalPrice()).compareTo(checkout.getShipingPrice()) == 0);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
    }
	
}
