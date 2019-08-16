package com.novaeslucas.modelo.internal.proxy;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

@SuppressWarnings("deprecation")
public class HttpSessionProxy implements HttpSession, Serializable {

	private static final long serialVersionUID = 1L;

	private transient final HttpSession delegate;

	public HttpSessionProxy(HttpSession httpSession) {
		this.delegate = httpSession;
	}

	public long getCreationTime() {
		return this.delegate.getCreationTime();
	}

	public String getId() {
		return this.delegate.getId();
	}

	public long getLastAccessedTime() {
		return this.delegate.getLastAccessedTime();
	}

	public ServletContext getServletContext() {
		return this.delegate.getServletContext();
	}

	public void setMaxInactiveInterval(int interval) {
		this.delegate.setMaxInactiveInterval(interval);
	}

	public int getMaxInactiveInterval() {
		return this.delegate.getMaxInactiveInterval();
	}

	@Deprecated
	public HttpSessionContext getSessionContext() {
		return this.delegate.getSessionContext();
	}

	public Object getAttribute(String name) {
		return this.delegate.getAttribute(name);
	}

	@Deprecated
	public Object getValue(String name) {
		return this.delegate.getValue(name);
	}

	@SuppressWarnings("unchecked")
	public Enumeration<String> getAttributeNames() {
		return this.delegate.getAttributeNames();
	}

	@Deprecated
	public String[] getValueNames() {
		return this.delegate.getValueNames();
	}

	public void setAttribute(String name, Object value) {
		this.delegate.setAttribute(name, value);
	}

	@Deprecated
	public void putValue(String name, Object value) {
		this.delegate.putValue(name, value);
	}

	public void removeAttribute(String name) {
		this.delegate.removeAttribute(name);
	}

	@Deprecated
	public void removeValue(String name) {
		this.delegate.removeValue(name);
	}

	public void invalidate() {
		this.delegate.invalidate();
	}

	public boolean isNew() {
		return this.delegate.isNew();
	}

}
