package com.novaeslucas.modelo.internal.producer;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.novaeslucas.modelo.internal.proxy.HttpSessionProxy;

public class HttpSessionProducer {

	@Produces
	@Default
	@SessionScoped
	public HttpSession create(final HttpServletRequest request) {
		return new HttpSessionProxy(request.getSession());
	}
}
