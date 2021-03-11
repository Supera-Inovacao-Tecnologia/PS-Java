package br.com.supera.game.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.supera.game.db.JPAEntityManager;
import br.com.supera.game.db.ProductDao;
import br.com.supera.game.resources.ProductResourceBeanParam;
import br.com.supera.game.store.Product;

@Provider
public class ProductService {

	private final Logger LOGGER;

	public ProductService() {
		LOGGER = LoggerFactory.getLogger(this.getClass());
	}

	public List<Product> getAllProductsOrderedAndPaginated(ProductResourceBeanParam beanParam) {

		LOGGER.debug("Getting all products paginated from getAllProductsPaginated");

		EntityManager em = JPAEntityManager.getInstance().getEntityManager();
		ProductDao productDao = new ProductDao(em);

		List<Product> productList = productDao.getAll();

		List<Product> productListPaginated = getAllProductsOrdered(beanParam.getOrderByQueryParam(), productList);
		return productListPaginated;
	}

	public List<Product> getAllProductsOrdered(String orderByField, List<Product> productList) {

		LOGGER.debug("Getting all products paginated from getAllProductsPaginated");

		// COMPARATORS

		Comparator<Product> compName = (p1, p2) -> p1.getName().compareTo(p2.getName());
		Comparator<Product> compPrice = (p1, p2) -> p1.getPrice().compareTo(p2.getPrice());
		Comparator<Product> compScore = (p1, p2) -> Integer.valueOf(p1.getScore())
				.compareTo(Integer.valueOf(p2.getScore()));
		Comparator<Product> compId = (p1, p2) -> p1.getId().compareTo(p2.getId());
		Comparator<Product> comp = null;

		/* We could use HashMap<String, Comparator> for large requirements */
		switch (orderByField) {
		case "name":
			comp = compName;
			break;
		case "price":
			comp = compPrice;
			break;
		case "score":
			comp = compScore;
			break;
		case "id":
			comp = compId;
			break;
		}

		List<Product> productListOrdered = null;

		productListOrdered = productList.stream().sorted(comp).collect(Collectors.toList());
		return productListOrdered;
	}
}
