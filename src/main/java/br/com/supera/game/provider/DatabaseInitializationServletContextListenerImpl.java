package br.com.supera.game.provider;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import br.com.supera.game.db.JPAEntityManager;
import br.com.supera.game.db.ProductDao;
import br.com.supera.game.db.UserDao;
import br.com.supera.game.model.User;
import br.com.supera.game.store.Product;

@WebListener
public class DatabaseInitializationServletContextListenerImpl implements ServletContextListener {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private final String PRODUCT_FILE_LOCATION = "datasets/product-splited-in-docs.yml";
	private final String USER_FILE_LOCATION = "datasets/users.yml";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.info("Trigering ServletContextListener for database initization");

		initializeDatabaseForProductionTest();

	}

	private void initializeDatabaseForProductionTest() {
		LOGGER.debug("Initializing database with Products");

		EntityManager em = JPAEntityManager.getInstance().getEntityManager();

		// INSERTING PRODUCTS

		ProductDao productDao = new ProductDao(em);

		List<Product> productList = getProductsFromYaml();

		productList.stream().forEach((p) -> {
			LOGGER.debug("Inserting {} to database", p.toString());
			productDao.persistAutoCommit(p);
		});

		// INSERTING UNIQUE USER

		UserDao userDao = new UserDao(em);
		userDao.persistAutoCommit(getUnique());

	}

	private List<Product> getProductsFromYaml() {
		LOGGER.debug("Loading Products objects from {}", PRODUCT_FILE_LOCATION);

		Yaml yaml = new Yaml(new Constructor(Product.class));
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(PRODUCT_FILE_LOCATION);

		Iterable<Object> i = yaml.loadAll(inputStream);

		List<Product> productList = new ArrayList<Product>();

		i.forEach((o) -> {

			productList.add((Product) o);
		});

		return productList;
	}

	private User getUnique() {
		LOGGER.debug("Loading unic User objects from {} for test purposes", PRODUCT_FILE_LOCATION);

		Yaml yaml = new Yaml(new Constructor(User.class));
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(USER_FILE_LOCATION);

		User user = yaml.load(inputStream);

		return user;
	}

}
