package com.novaeslucas.modelo.internal.producer;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServletRequest;

import com.novaeslucas.modelo.internal.util.Faces;

public class HttpServletRequestProducer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@RequestScoped
	public HttpServletRequest create() {
		return (HttpServletRequest) Faces.getFacesContext().getExternalContext().getRequest();
	}
}
