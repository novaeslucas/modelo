package com.novaeslucas.modelo.internal.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.ApplicationException;
import javax.enterprise.context.ContextNotActiveException;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.novaeslucas.modelo.internal.message.Message;
import com.novaeslucas.modelo.internal.message.SeverityType;

public class Faces {

		
	public static void addMessages(final List<Message> messages) {
		if (messages != null) {
			for (Message m : messages) {
				addMessage(m);
			}
		}
	}

	public static void addMessage(final Message message) {
		getFacesContext().addMessage(null, parse(message));
	}

	public static void addMessage(final String clientId, final Message message) {
		getFacesContext().addMessage(clientId, parse(message));
	}
	
	public static void addMessage(final String text, final SeverityType severity){
		Message message = new JsfMessage(text, severity);
		addMessage(message);
	}

	public static FacesContext getFacesContext() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		if (ctx == null) {
			throw new ContextNotActiveException("facescontext.no.active");
		}

		return ctx;
	}

	public static Severity parse(final SeverityType severityType) {
		Severity result = null;

		switch (severityType) {
			case INFO:
				result = FacesMessage.SEVERITY_INFO;
				break;
			case WARN:
				result = FacesMessage.SEVERITY_WARN;
				break;
			case ERROR:
				result = FacesMessage.SEVERITY_ERROR;
				break;
			case FATAL:
				result = FacesMessage.SEVERITY_FATAL;
		}

		return result;
	}

	public static FacesMessage parse(final Message message) {
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(parse(message.getSeverity()));
		facesMessage.setSummary(message.getText());
		return facesMessage;
	}
	
	public static FacesMessage parse(final Throwable throwable) {
		FacesMessage facesMessage = new FacesMessage();
		ApplicationException annotation = throwable.getClass().getAnnotation(ApplicationException.class);

		if (annotation != null) {
			facesMessage.setSeverity(parse(SeverityType.ERROR));
		} else {
			// TODO Tratar o parse de exceções que não são ApplicationException
		}

		if (throwable.getMessage() != null) {
			facesMessage.setSummary(throwable.getMessage());
		} else {
			facesMessage.setSummary(throwable.toString());
		}

		return facesMessage;
	}
	
	public static Map<String, Object> getViewMap() {
		UIViewRoot viewRoot = getFacesContext().getViewRoot();
		return viewRoot.getViewMap(true);
	}
	
	public static boolean isPostback(){
		return getFacesContext().isPostback();
	}
	
	public static void redirectView(String viewId){
		NavigationHandler navigation = Faces.getFacesContext().getApplication().getNavigationHandler();
		navigation.handleNavigation(Faces.getFacesContext(), null, viewId);
		Faces.getFacesContext().renderResponse();
	}
	
	public static void sendRedirect(String viewId){
		try {
			HttpServletRequest request = (HttpServletRequest) Faces.getRequest();
			HttpServletResponse response = (HttpServletResponse) Faces.getResponse();
			response.sendRedirect(request.getContextPath() +  viewId);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static Object convert(final String value, final Converter converter) {
		Object result = null;

		if (!Strings.isEmpty(value)) {
			if (converter != null) {
				result = converter.getAsObject(getFacesContext(), getFacesContext().getViewRoot(), value);
			} else {
				result = new String(value);
			} 
		}

		return result;
	}
		
	public static HttpServletResponse getResponse(){
		return (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
	}
	
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}
	
	public static HttpSession getSession(boolean arg0){
		return (HttpSession) getFacesContext().getExternalContext().getSession(arg0);
	}
	
	public static Converter getConverter(Class<?> clazz) {
		Converter result;

		try {
			Application application = getFacesContext().getApplication();
			result = application.createConverter(clazz);

		} catch (Exception e) {
			result = null;
		}

		return result;
	}
	
}
