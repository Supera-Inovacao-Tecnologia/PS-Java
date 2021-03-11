package br.com.supera.game.service;

import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.supera.game.db.JPAEntityManager;
import br.com.supera.game.db.ProductDao;
import br.com.supera.game.store.Cart;
import br.com.supera.game.store.Checkout;
import br.com.supera.game.store.Product;

public class CheckoutService {
		
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	BigDecimal shippingPrice = BigDecimal.valueOf(10);// TODO get from .properties file
	BigDecimal noShippingPriceThreshold = BigDecimal.valueOf(250);// TODO get from .properties file

	public Checkout getCheckoutFromCart(Cart cart) {

		BigDecimal shippingTotalPrice = getShippingPrice(cart);
		BigDecimal productsTotalPrice = getProductsTotalPrice(cart);
		BigDecimal TotalPrice = getTotalPrice(productsTotalPrice, shippingTotalPrice);

		Checkout checkout = new Checkout();

		checkout.setProductList(cart.getResourceRepresentation());
		
		checkout.setSubtotalPrice(productsTotalPrice);

		checkout.setShipingPrice(shippingTotalPrice);
		
		checkout.setTotalPrice(TotalPrice);
		
		//If the quotient is not the shipping price, the shipping price is not inserted in the total price
		if (TotalPrice.subtract(productsTotalPrice).compareTo(shippingTotalPrice) != 0) {
			LOGGER.debug("Shipping must NOT be paid");
			checkout.setShippingPriceStatus(false); 
			
		}
			
		return checkout;

	}

	private BigDecimal getShippingPrice(Cart cart) {

		Map<Integer, Integer> prodQuantPrice = cart.getProductIdQuantMap();

		int totalQuantity = 0; // a priori

		for (Integer i : prodQuantPrice.values()) {

			totalQuantity += i;
		}
		
		//multiply the number of products by the individual shipping price
		return this.shippingPrice.multiply(BigDecimal.valueOf(totalQuantity));
	}

	private BigDecimal getProductsTotalPrice(Cart cart) {

		Map<Integer, Integer> prodQuantPriceMap = cart.getProductIdQuantMap();

		BigDecimal totalProductPrice = BigDecimal.valueOf(0); // a priori

		EntityManager em = JPAEntityManager.getInstance().getEntityManager();
		ProductDao pDao = new ProductDao(em);

		for (Integer productId : prodQuantPriceMap.keySet()) {

			Product product = pDao.getById(productId);
			
			BigDecimal productQuantity = BigDecimal.valueOf(prodQuantPriceMap.get(product.getId()));

			totalProductPrice = totalProductPrice.add(product.getPrice().multiply(productQuantity));
		}

		return totalProductPrice;

	}

	private BigDecimal getTotalPrice(Cart cart) {

		BigDecimal total = getProductsTotalPrice(cart); // a priori

		if (total.compareTo(this.noShippingPriceThreshold) > 0) {
			
			LOGGER.debug("Shipping must MUST be paid");
			
			total = total.add(getShippingPrice(cart));

		}

		return total;
	}

	private BigDecimal getTotalPrice(BigDecimal productsTotalPrice, BigDecimal shippingTotalPrice) {

		BigDecimal total = productsTotalPrice; // a priori
		
		//If productsTotalPrice is less or equal 250
		if (total.compareTo(this.noShippingPriceThreshold) <= 0) {

			total = total.add(shippingTotalPrice);

		}

		return total;
	}

}
