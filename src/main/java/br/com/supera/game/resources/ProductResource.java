package br.com.supera.game.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.supera.game.service.ProductService;
import br.com.supera.game.store.Product;

@Path("products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

	private final Logger LOGGER;
	
	public ProductResource() {
		this.LOGGER = LoggerFactory.getLogger(this.getClass());
	}
	
	@GET
	public Response getProducts(@BeanParam ProductResourceBeanParam paginationBeanParam) {
		
		//Could be written using refletion
		LOGGER.debug("Start getProducts method from ProductResource");
		
		ProductService ps = new ProductService();
		
		List<Product> pListOrderedAndPaginated = ps.getAllProductsOrderedAndPaginated(paginationBeanParam);
		
		//GenericEntity maintain the size of collections in order to Jackson be able to serialize it
		GenericEntity<List<Product>> ge = new GenericEntity<List<Product>>(pListOrderedAndPaginated){};
		
		return Response.ok().entity(ge).build();
	}
}
