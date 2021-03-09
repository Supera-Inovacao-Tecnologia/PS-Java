package br.com.supera.game.store;

import java.math.BigDecimal;
import java.util.List;

import br.com.supera.game.db.AbstractEntity;

public class Checkout extends AbstractEntity {
	
	private List<Product> productList;
	private BigDecimal shipingPrice;
	private BigDecimal subtotalPrice;
	private BigDecimal totalPrice;
	
	
	/* CONSTRUCTORS */
	
	/*It is only possible to get an up to date instance of a cart's checkout */
	public Checkout(Integer cartId) {
		super();
		
		//Access the up to date cart from the database
	}

	/* GETTERS/SETTERS */

	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public BigDecimal getShipingPrice() {
		return shipingPrice;
	}
	public void setShipingPrice(BigDecimal shipingPrice) {
		this.shipingPrice = shipingPrice;
	}
	public BigDecimal getSubtotalPrice() {
		return subtotalPrice;
	}
	public void setSubtotalPrice(BigDecimal subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
