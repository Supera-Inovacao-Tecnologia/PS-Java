package br.com.supera.game.resources;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutResource {
	
	private Logger LOGGER;
	
	public CheckoutResource() {
		LOGGER = LoggerFactory.getLogger(this.getClass());
	}
	
	
	@GET
	public Response getCart() {
		LOGGER.debug("Start getCart method");
		return Response
				.status(Status.OK)
				.entity("Checkout Ok")
				.build();
	}
	
	
}
