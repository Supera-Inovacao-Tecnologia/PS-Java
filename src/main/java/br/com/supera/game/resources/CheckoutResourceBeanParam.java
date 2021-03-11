package br.com.supera.game.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class CheckoutResourceBeanParam {

	@DefaultValue("1")
	@QueryParam("pageNumber")
	private String pageNumberQueryParam;

	@DefaultValue("name")
	@QueryParam("orderBy")
	private String orderByQueryParam;

	@PathParam("userId")
	private Integer userIdQueryParam;

	public String getPageNumberQueryParam() {
		return pageNumberQueryParam;
	}

	public void setPageNumberQueryParam(String pageNumberQueryParam) {
		this.pageNumberQueryParam = pageNumberQueryParam;
	}

	public String getOrderByQueryParam() {
		return orderByQueryParam;
	}

	public void setOrderByQueryParam(String orderByQueryParam) {
		this.orderByQueryParam = orderByQueryParam;
	}

	public Integer getUserIdQueryParam() {
		return userIdQueryParam;
	}

	public void setUserIdQueryParam(Integer userIdQueryParam) {
		this.userIdQueryParam = userIdQueryParam;
	}

}
