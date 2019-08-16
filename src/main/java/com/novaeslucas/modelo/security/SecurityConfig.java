package com.novaeslucas.modelo.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SecurityConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String SHIRO = "shiro";
	public static final String PROVIDER = "provider";
	public static final String UNAUTHORIZEDURL = "roles.unauthorizedUrl";

	public static Map<String, Object> getAttribute() {
		return attribute;
	}

	public static void setAttribute(Map<String, Object> attribute) {
		SecurityConfig.attribute = attribute;
	}

	private static Map<String, Object> attribute = new HashMap<String, Object>();
	
	
	
}
