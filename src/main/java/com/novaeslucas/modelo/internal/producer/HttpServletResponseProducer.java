package com.novaeslucas.modelo.internal.producer;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServletResponse;

import com.novaeslucas.modelo.internal.util.Faces;

public class HttpServletResponseProducer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Produces
	@RequestScoped
	public HttpServletResponse create() {
		return (HttpServletResponse) Faces.getFacesContext().getExternalContext().getResponse();
	}

}
