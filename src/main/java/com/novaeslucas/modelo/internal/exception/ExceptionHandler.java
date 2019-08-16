package com.novaeslucas.modelo.internal.exception;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.util.RedirectView;
import org.slf4j.Logger;

import com.novaeslucas.modelo.internal.producer.LoggerProducer;
import com.novaeslucas.modelo.internal.producer.ResourceBundleProducer;
import com.novaeslucas.modelo.internal.util.Exceptions;
import com.novaeslucas.modelo.internal.util.Faces;
import com.novaeslucas.modelo.internal.util.ResourceBundle;
import com.novaeslucas.modelo.security.SecurityConfig;

public class ExceptionHandler extends ExceptionHandlerWrapper {

	private javax.faces.context.ExceptionHandler exceptionHandler;
	private ResourceBundle bundle = new ResourceBundleProducer().create("framework");
	private static Logger logger;
	
	static{
		logger = new LoggerProducer().create(ExceptionHandler.class);
	}

	public ExceptionHandler(final javax.faces.context.ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	@Override
	public javax.faces.context.ExceptionHandler getWrapped() {
		return this.exceptionHandler;
	}
	
	@Override
	public void handle() throws FacesException {
		boolean handled;
		
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			Throwable cause = getException(context.getException());
			handled = cacthException(cause);
			
			if (handled) {
				i.remove();
			}
			
			if(!handled){
				i.remove();
				redirect(cause);
			}
		}
	}
	
	private Throwable getException(final Throwable throwable) {
		Throwable root = throwable;
		while (root.getCause() != null) {
			root = root.getCause();
		}
		return root;
	}
	
	public boolean cacthException(final Throwable cause){
		boolean handled = false;

		if (Exceptions.isApplicationException(cause)) {
			FacesMessage message = Faces.parse(cause);
			Faces.getFacesContext().addMessage(null, message);

			handled = true;
		} else if (Exceptions.isAuthorizationException(cause)){
			handled = false;
		}
		
		logger.error(cause.getMessage(), cause);
		
		return handled;
	}
	
	private boolean containsProdiver(){
		return SecurityConfig.getAttribute().containsKey(SecurityConfig.PROVIDER);
	}
	
	private boolean isProviderShiro(){
		return (SecurityConfig.SHIRO.equals(SecurityConfig.getAttribute().get(SecurityConfig.PROVIDER)));
	}
	
	private boolean hasUnauthorizedUrl(){
		return SecurityConfig.getAttribute().containsKey(SecurityConfig.UNAUTHORIZEDURL);
	}
	
	private String getUnauthorizedUrl(){
		return (String) SecurityConfig.getAttribute().get(SecurityConfig.UNAUTHORIZEDURL);
	}
	
	private void createRequestParams(HttpServletRequest request, final Throwable cause){
		request.setAttribute("exceptionMessage", cause.getMessage());
		StringBuilder stackTrace = new StringBuilder();
		StackTraceElement[] elements = cause.getStackTrace();
		
		for (StackTraceElement element : elements) {
			stackTrace.append(element.toString());
		}
		
		request.setAttribute("exceptionStackTrace", stackTrace.toString() + "<br />");
	}
	
	private String getRedirectView(final Throwable cause){
		String viewId = null;
		
		if(Exceptions.isAuthorizationException(cause) && containsProdiver() && isProviderShiro() && hasUnauthorizedUrl()){
			viewId = getUnauthorizedUrl();
		} else if(bundle.containsKey(cause.getClass().getCanonicalName())){
			viewId = bundle.getString(cause.getClass().getCanonicalName());
		} else if(viewId == null && bundle.containsKey("exception.redirect.view")){
			viewId = bundle.getString("exception.redirect.view");
		}
		
		return viewId;
	}
	
	
	public void redirect(final Throwable cause){
		String viewId = null;
		
		HttpServletRequest request = Faces.getRequest();
		HttpServletResponse response = Faces.getResponse();
		
		createRequestParams(request, cause);

		viewId = getRedirectView(cause);
		
		if(viewId == null) {
			Faces.getRequest().setAttribute("renderedHandlerPopup", true);
			return;
		}
		
		RedirectView view = new RedirectView(viewId, true, true);
		try{
			view.renderMergedOutputModel(null, request, response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	

}
