package br.com.supera.game.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

import br.com.supera.game.db.AbstractEntity;
import br.com.supera.game.db.JPAEntityManager;
import br.com.supera.game.db.ProductDao;

@Entity
public class Cart extends AbstractEntity{
	
	@ElementCollection
	private Map<Integer, Integer> productIdQuantMap;
	
	//CONSTRUCTORS

	
	public Cart(List<Product> productList) {
		super();
		this.productIdQuantMap = new HashMap<Integer, Integer>();
	}

	public Cart() {
		super();
	}
	
	public void addProduct(Product newProduct) {
		boolean prodAlreadyInTheMap = false; //a priori
		
		for( Integer productId : productIdQuantMap.keySet()) {
			
			if(newProduct.getId().equals(productId)) {
				
				prodAlreadyInTheMap = true;
				break;
			}
		}
		
		if(prodAlreadyInTheMap) {
			int quant = productIdQuantMap.get(newProduct.getId());
			
			productIdQuantMap.put(newProduct.getId(), quant+1);
		}  
		else {
			//The first product of this type in the cart
			productIdQuantMap.put(newProduct.getId(), 1);
		}
	}
	
	public void removeProduct(Product newProduct) {
		boolean prodAlreadyInTheMap = false; //a priori
		
		for( Integer productId : productIdQuantMap.keySet()) {
			
			if(newProduct.getId().equals(productId)) {
				
				prodAlreadyInTheMap = true;
				break;
			}
		}
		
		if(!prodAlreadyInTheMap) {
			
			return;
		}  
		else {
			//The cart has product of this type
			
			int quant = productIdQuantMap.get(newProduct.getId());
			
			productIdQuantMap.put(newProduct.getId(), quant-1);
			
			if(productIdQuantMap.get(newProduct.getId()) < 0){
				productIdQuantMap.put(newProduct.getId(), 0);
			}
		}
	}
	
	//Transform the map in a list
	public List<Product> getResourceRepresentation(){
		List<Product> productList = new ArrayList<Product>();
		
		EntityManager em = JPAEntityManager.getInstance().getEntityManager();
		ProductDao prodDao = new ProductDao(em);
		
		for( Entry<Integer,Integer> es : productIdQuantMap.entrySet()) {
			
			for(int i = 0 ; i < es.getValue() ; i++ ) {
				productList.add(prodDao.getById(es.getKey()));
			}
		}
		
		return productList;
	}

	// GETTERS/SETTERS
	public Map<Integer, Integer> getProductIdQuantMap() {
		return productIdQuantMap;
	}

	public void setProductIdQuantMap(Map<Integer, Integer> productIdQuantMap) {
		this.productIdQuantMap = productIdQuantMap;
	}
	
	

}
