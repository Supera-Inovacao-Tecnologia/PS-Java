package br.com.supera.game.resources;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.supera.game.db.JPAEntityManager;
import br.com.supera.game.db.UserDao;
import br.com.supera.game.model.User;
import br.com.supera.game.store.Product;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {

	private Logger LOGGER;

	public CartResource() {
		LOGGER = LoggerFactory.getLogger(this.getClass());
	}

	@GET
	public Response getCart(@PathParam("userId") Integer userId) {

		LOGGER.debug("Getting cart for user with id {} ", userId);

		EntityManager em = JPAEntityManager.getInstance().getEntityManager();

		User user = getUser(userId);

		if (user == null)
			return Response.status(Status.NOT_FOUND).build();

		return Response.status(Status.OK).entity(user.getCart()).build();
	}

	@POST
	public Response postCart(@PathParam("userId") Integer userId, Product p) {

		User user = getUser(userId);

		if (user == null)
			return Response.status(Status.NOT_FOUND).build();

		user.getCart().addProduct(p);

		EntityManager em = JPAEntityManager.getInstance().getEntityManager();

		new UserDao(em).mergeAutoCommit(user);

		return Response.status(Status.CREATED).entity(user.getCart().getResourceRepresentation()).build();
	}

	@PUT
	public Response putCart(@PathParam("userId") Integer userId, Product p) {
		return Response.status(Status.OK).entity("Cart Ok").build();
	}

	@DELETE
	public Response deleteCart(@PathParam("userId") Integer userId, Product p) {
		User user = getUser(userId);

		if (user == null)
			return Response.status(Status.NOT_FOUND).build();

		user.getCart().removeProduct(p);

		EntityManager em = JPAEntityManager.getInstance().getEntityManager();

		new UserDao(em).mergeAutoCommit(user);

		return Response.status(Status.CREATED).entity(user.getCart().getResourceRepresentation()).build();
	}

	private User getUser(Integer userId) {

		EntityManager em = JPAEntityManager.getInstance().getEntityManager();

		return new UserDao(em).getByField("id", String.valueOf(userId));

	}

	// SUBRESOURCE LOCATOR
	@Path("checkout")
	public CheckoutResource getCheckoutResource() throws RuntimeException {
		LOGGER.debug("Subresource locator to CheckoutResource");
		return new CheckoutResource();
	}
}
