package br.com.supera.game.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("cart")
public class CartResource {
	
	private Logger LOGGER;
	
	public CartResource() {
		LOGGER = LoggerFactory.getLogger(this.getClass());
	}
	
	@Path("checkout")
	public CheckoutResource getCheckout() throws RuntimeException {
		LOGGER.debug("Subresource locator to CheckoutResource");
		return new CheckoutResource();
	}
	
	@GET
	public Response getCart() {
		return Response
				.status(Status.OK)
				.entity("Cart Ok")
				.build();
	}
	
}
