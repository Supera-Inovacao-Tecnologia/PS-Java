package br.com.supera.game.store;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.supera.game.db.AbstractEntity;

@Entity
public class Checkout extends AbstractEntity {

	@OneToMany
	private List<Product> productList;
	private BigDecimal shipingPrice;
	private Boolean shippingPriceStatus;
	private BigDecimal subtotalPrice;
	private BigDecimal totalPrice;

	/* CONSTRUCTORS */

	public Checkout() {
		super();
		this.shippingPriceStatus = true; // a priori
	}

//	/*It is only possible to get an up to date instance of a cart's checkout */
//	public Checkout(Integer cartId) {
//		super();
//		
//		//Access the up to date cart from the database
//	}
//	

	/* GETTERS/SETTERS */

	public List<Product> getProductList() {
		return productList;
	}

	public BigDecimal getShipingPrice() {
		return shipingPrice;
	}

	public Boolean getShippingPriceStatus() {
		return shippingPriceStatus;
	}

	public BigDecimal getSubtotalPrice() {
		return subtotalPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public void setShipingPrice(BigDecimal shipingPrice) {
		this.shipingPrice = shipingPrice;
	}

	public void setShippingPriceStatus(Boolean shippingPriceStatus) {
		this.shippingPriceStatus = shippingPriceStatus;
	}

	public void setSubtotalPrice(BigDecimal subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
