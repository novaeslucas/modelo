package com.novaeslucas.modelo.internal.proxy;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.Marker;

public class Slf4jLoggerProxy implements Logger, Serializable {

	private static final long serialVersionUID = 1L;

	private transient final Logger delegate;

	public Slf4jLoggerProxy(final Logger logger) {
		this.delegate = logger;
	}

	public void debug(final Marker marker, final String msg) {
		this.delegate.debug(marker, msg);
	}

	public void debug(final Marker marker, final String format, final Object arg) {
		this.delegate.debug(marker, format, arg);
	}

	public void debug(final Marker marker, final String format, final Object arg1, final Object arg2) {
		this.delegate.debug(marker, format, arg1, arg2);
	}

	public void debug(final Marker marker, final String format, final Object[] argArray) {
		this.delegate.debug(marker, format, argArray);
	}

	public void debug(final Marker marker, final String msg, final Throwable t) {
		this.delegate.debug(marker, msg, t);
	}

	public void debug(final String msg) {
		this.delegate.debug(msg);
	}

	public void debug(final String format, final Object arg) {
		this.delegate.debug(format, arg);
	}

	public void debug(final String format, final Object arg1, final Object arg2) {
		this.delegate.debug(format, arg1, arg2);
	}

	public void debug(final String format, final Object[] argArray) {
		this.delegate.debug(format, argArray);
	}

	public void debug(final String msg, final Throwable t) {
		this.delegate.debug(msg, t);
	}

	public void error(final Marker marker, final String msg) {
		this.delegate.error(marker, msg);
	}

	public void error(final Marker marker, final String format, final Object arg) {
		this.delegate.error(marker, format, arg);
	}

	public void error(final Marker marker, final String format, final Object arg1, final Object arg2) {
		this.delegate.error(marker, format, arg1, arg2);
	}

	public void error(final Marker marker, final String format, final Object[] argArray) {
		this.delegate.error(marker, format, argArray);
	}

	public void error(final Marker marker, final String msg, final Throwable t) {
		this.delegate.error(marker, msg, t);
	}

	public void error(final String msg) {
		this.delegate.error(msg);
	}

	public void error(final String format, final Object arg) {
		this.delegate.error(format, arg);
	}

	public void error(final String format, final Object arg1, final Object arg2) {
		this.delegate.error(format, arg1, arg2);
	}

	public void error(final String format, final Object[] argArray) {
		this.delegate.error(format, argArray);
	}

	public void error(final String msg, final Throwable t) {
		this.delegate.error(msg, t);
	}

	public String getName() {
		return this.delegate.getName();
	}

	public void info(final Marker marker, final String msg) {
		this.delegate.info(marker, msg);
	}

	public void info(final Marker marker, final String format, final Object arg) {
		this.delegate.info(marker, format, arg);
	}

	public void info(final Marker marker, final String format, final Object arg1, final Object arg2) {
		this.delegate.info(marker, format, arg1, arg2);
	}

	public void info(final Marker marker, final String format, final Object[] argArray) {
		this.delegate.info(marker, format, argArray);
	}

	public void info(final Marker marker, final String msg, final Throwable t) {
		this.delegate.info(marker, msg, t);
	}

	public void info(final String msg) {
		this.delegate.info(msg);
	}

	public void info(final String format, final Object arg) {
		this.delegate.info(format, arg);
	}

	public void info(final String format, final Object arg1, final Object arg2) {
		this.delegate.info(format, arg1, arg2);
	}

	public void info(final String format, final Object[] argArray) {
		this.delegate.info(format, argArray);
	}

	public void info(final String msg, final Throwable t) {
		this.delegate.info(msg, t);
	}

	public boolean isDebugEnabled() {
		return this.delegate.isDebugEnabled();
	}

	public boolean isDebugEnabled(final Marker marker) {
		return this.delegate.isDebugEnabled(marker);
	}

	public boolean isErrorEnabled() {
		return this.delegate.isErrorEnabled();
	}

	public boolean isErrorEnabled(final Marker marker) {
		return this.delegate.isErrorEnabled(marker);
	}

	public boolean isInfoEnabled() {
		return this.delegate.isInfoEnabled();
	}

	public boolean isInfoEnabled(final Marker marker) {
		return this.delegate.isInfoEnabled(marker);
	}

	public boolean isTraceEnabled() {
		return this.delegate.isTraceEnabled();
	}

	public boolean isTraceEnabled(final Marker marker) {
		return this.delegate.isTraceEnabled(marker);
	}

	public boolean isWarnEnabled() {
		return this.delegate.isWarnEnabled();
	}

	public boolean isWarnEnabled(final Marker marker) {
		return this.delegate.isWarnEnabled(marker);
	}

	public void trace(final Marker marker, final String msg) {
		this.delegate.trace(marker, msg);
	}

	public void trace(final Marker marker, final String format, final Object arg) {
		this.delegate.trace(marker, format, arg);
	}

	public void trace(final Marker marker, final String format, final Object arg1, final Object arg2) {
		this.delegate.trace(marker, format, arg1, arg2);
	}

	public void trace(final Marker marker, final String format, final Object[] argArray) {
		this.delegate.trace(marker, format, argArray);
	}

	public void trace(final Marker marker, final String msg, final Throwable t) {
		this.delegate.trace(marker, msg, t);
	}

	public void trace(final String msg) {
		this.delegate.trace(msg);
	}

	public void trace(final String format, final Object arg) {
		this.delegate.trace(format, arg);
	}

	public void trace(final String format, final Object arg1, final Object arg2) {
		this.delegate.trace(format, arg1, arg2);
	}

	public void trace(final String format, final Object[] argArray) {
		this.delegate.trace(format, argArray);
	}

	public void trace(final String msg, final Throwable t) {
		this.delegate.trace(msg, t);
	}

	public void warn(final Marker marker, final String msg) {
		this.delegate.warn(marker, msg);
	}

	public void warn(final Marker marker, final String format, final Object arg) {
		this.delegate.warn(marker, format, arg);
	}

	public void warn(final Marker marker, final String format, final Object arg1, final Object arg2) {
		this.delegate.warn(marker, format, arg1, arg2);
	}

	public void warn(final Marker marker, final String format, final Object[] argArray) {
		this.delegate.warn(marker, format, argArray);
	}

	public void warn(final Marker marker, final String msg, final Throwable t) {
		this.delegate.warn(marker, msg, t);
	}

	public void warn(final String msg) {
		this.delegate.warn(msg);
	}

	public void warn(final String format, final Object arg) {
		this.delegate.warn(format, arg);
	}

	public void warn(final String format, final Object arg1, final Object arg2) {
		this.delegate.warn(format, arg1, arg2);
	}

	public void warn(final String format, final Object[] argArray) {
		this.delegate.warn(format, argArray);
	}

	public void warn(final String msg, final Throwable t) {
		this.delegate.warn(msg, t);
	}

}
