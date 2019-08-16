package com.novaeslucas.modelo.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@Named
@SessionScoped
@ManagedBean(name="menuBean")
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String cadastrarLocal(){
		return "pretty:cadastrar-local";
	}
	
}
