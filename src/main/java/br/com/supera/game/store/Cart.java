package br.com.supera.game.store;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.supera.game.db.AbstractEntity;

@Entity
public class Cart extends AbstractEntity{
	
	@OneToMany
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
