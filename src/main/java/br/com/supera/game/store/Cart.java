package br.com.supera.game.store;

import java.util.List;

import br.com.supera.game.db.AbstractEntity;

public class Cart extends AbstractEntity{
	
	private List<Product> productList;
	
	//CONSTRUCTORS

	public Cart(List<Product> productList) {
		super();
		this.productList = productList;
	}

	public Cart() {
		super();
	}
	
	// GETTERS/SETTERS
	
	public List<Product> getProductList() {
		return productList;
	}

	
	public void addProduct(Integer productIndex) {
		productList.remove(productIndex);
	}
	
	public void removeProduct(Integer productIndex) {
		productList.remove(productIndex);
	}
}
