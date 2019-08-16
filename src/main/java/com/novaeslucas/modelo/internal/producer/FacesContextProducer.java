package com.novaeslucas.modelo.internal.producer;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer {

	@Produces
	@RequestScoped
	@Default
	public FacesContext create() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		if (ctx == null) {
			throw new ContextNotActiveException("FacesContext isn't active");
		}

		return ctx;
	}

}
