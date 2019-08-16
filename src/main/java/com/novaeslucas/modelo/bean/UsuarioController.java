package com.novaeslucas.modelo.bean;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.novaeslucas.modelo.domain.Usuario;
import com.novaeslucas.modelo.internal.annotation.Bundle;
import com.novaeslucas.modelo.internal.annotation.ViewScoped;
import com.novaeslucas.modelo.internal.message.SeverityType;
import com.novaeslucas.modelo.internal.template.ControllerSupport;
import com.novaeslucas.modelo.internal.util.Faces;
import com.novaeslucas.modelo.internal.util.ResourceBundle;
import com.novaeslucas.modelo.service.UsuarioService;

@Named
@ViewScoped
public class UsuarioController extends ControllerSupport<Usuario, UsuarioService> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	@Bundle(name = "MessageResources")
	private ResourceBundle bundle;
	
	private String login;
	
	private String email;
	
	private String novaSenha;
	
	private boolean confirmacaoEsqueciMinhaSenha;
	
	private Usuario usuarioSelected;
	
	private List<Usuario> usuarios;
	
	public void cadastrar() {
		usuarioService.cadastrar(getDomain());
		Faces.addMessage(bundle.getString("cadastrar"), SeverityType.INFO);
		setDomain(new Usuario());
	}
	
	public String preEditar(){
		Faces.getRequest().setAttribute("id", usuarioSelected.getId());
		return "pretty:editar-usuario";
	}
	
	public void editar(){
		usuarioService.editar(getDomain());
		Faces.addMessage(bundle.getString("editar"), SeverityType.INFO);
	}

	public void buscar() {
		usuarios = usuarioService.buscar(getDomain());
	}
	
	public void remover(){
		usuarioService.remover(usuarioSelected);
		Faces.addMessage(bundle.getString("remover"), SeverityType.INFO);
		usuarios.remove(usuarioSelected);
	}

	public Subject obterSubject(){
		return SecurityUtils.getSubject();
	}

	@Override
	protected UsuarioService getService() {
		return usuarioService;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String preAlterarSenha(){
		return "pretty:usuario-alterar-senha";
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	public void alterarSenha(){
		String usuarioLogin = SecurityUtils.getSubject().getPrincipal().toString();
		
		if(usuarioLogin != null && !usuarioLogin.isEmpty()){
			usuarioService.alterarSenha(novaSenha, usuarioLogin);
			Faces.addMessage(bundle.getString("usuariocontroller.senhaAlterada"), SeverityType.INFO);			
		}	
	}
	
	public String esqueciMinhaSenha(){
		usuarioService.esqueciMinhaSenha(getDomain().getEmail());
		Faces.addMessage(bundle.getString("usuariocontroller.esqueciMinhaSenha"), SeverityType.INFO);
		return "pretty:login";
	}
	
	public void verificarToken(){
		String token = Faces.getRequest().getAttribute("token").toString();
		if(token != null && !token.isEmpty()){
			confirmacaoEsqueciMinhaSenha = usuarioService.verificarToken(token);
		}
	}

	public boolean isConfirmacaoEsqueciMinhaSenha() {
		return confirmacaoEsqueciMinhaSenha;
	}

	public void setConfirmacaoEsqueciMinhaSenha(boolean confirmacaoEsqueciMinhaSenha) {
		this.confirmacaoEsqueciMinhaSenha = confirmacaoEsqueciMinhaSenha;
	}

	public Usuario getUsuarioSelected() {
		return usuarioSelected;
	}

	public void setUsuarioSelected(Usuario usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}