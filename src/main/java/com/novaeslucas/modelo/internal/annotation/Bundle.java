package com.novaeslucas.modelo.internal.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;


@Qualifier
@Documented
@Target({ TYPE, FIELD, METHOD, PARAMETER })
@Retention(RUNTIME)
public @interface Bundle {

	@Nonbinding
	String name();
	
}
