package com.novaeslucas.modelo.internal.util;

import javax.ejb.ApplicationException;

import org.apache.shiro.authz.AuthorizationException;

public class Exceptions {

	public static boolean isApplicationException(final Throwable throwable) {
		return throwable.getClass().isAnnotationPresent(ApplicationException.class);
	}
	
	public static boolean isAuthorizationException(final Throwable throwable){
		return (throwable instanceof AuthorizationException);
	}

	public static void handleToRuntimeException(final Throwable throwable) throws RuntimeException {
		if (throwable instanceof RuntimeException) {
			throw (RuntimeException) throwable;
		} else {
			throw new RuntimeException(throwable);
		}
	}
}
