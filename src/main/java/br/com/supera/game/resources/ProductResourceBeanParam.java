package br.com.supera.game.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class ProductResourceBeanParam {
	
	@DefaultValue("1")
	@QueryParam("pageNumber")
	private String pageNumberQueryParam;
	
	@DefaultValue("name")
	@QueryParam("orderBy")
	private String orderByQueryParam;

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
	
	
}
