package br.com.supera.game.resources;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("users/{userId}")
public class UserResource {

	private Logger LOGGER;

	public UserResource() {
		LOGGER = LoggerFactory.getLogger(this.getClass());
	}

	@GET
	public Response getUser(@PathParam("userId") Integer userId) {

		// Fake registered user
		EntityManager em = JPAEntityManager.getInstance().getEntityManager();
		User u = new UserDao(em).getByField("id", String.valueOf(userId));

		// It would be more scalable using ExceptionMapper feature
		if (u == null)
			return Response.status(Status.NOT_FOUND).build();

		return Response.status(Status.OK).entity(u).build();
	}

	// SUBRESOURCE LOCATORS

	@Path("cart")
	public CartResource getCart() throws RuntimeException {
		LOGGER.debug("Subresource locator to CartResource");
		return new CartResource();
	}

}
