package com.novaeslucas.modelo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.novaeslucas.modelo.domain.Usuario;
import com.novaeslucas.modelo.exception.ChaveNaoEncontradaException;
import com.novaeslucas.modelo.exception.UsuarioInexistenteException;
import com.novaeslucas.modelo.internal.template.ServiceSupport;
import com.novaeslucas.modelo.persistence.UsuarioPersistence;
import com.novaeslucas.modelo.util.AppUtil;
import com.novaeslucas.modelo.util.GeradorDeSenhaUtil;

@Stateless
public class UsuarioService extends ServiceSupport<Usuario, UsuarioPersistence> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioPersistence usuarioPersistence;

	@Inject
	private GeradorDeSenhaUtil geradorDeSenhaUtil;

	public Usuario buscarPorLogin(Usuario usuario) {
		List<Usuario> lista = usuarioPersistence.buscarPorLogin(usuario);
		
		if(lista != null && !lista.isEmpty()){
			return lista.get(0);
		}else{
			//TODO Caso tamanho seja 0 retornar exception.
			return new Usuario();
		}
	}

    public Usuario buscarPorEmail(Usuario usuario) {
        List<Usuario> lista = usuarioPersistence.buscarPorEmail(usuario);

        if(lista != null && !lista.isEmpty()){
            return lista.get(0);
        }else{
            //TODO Caso tamanho seja 0 retornar exception.
            return new Usuario();
        }
    }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrar(Usuario domain){
		String senha = geradorDeSenhaUtil.gerarSenha();
		domain.setSenha(geradorDeSenhaUtil.gerarMD5(senha));
		usuarioPersistence.insert(domain);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterarSenha(String novaSenha, String usuarioLogin){
		Usuario usuario = new Usuario();
		usuario.setLogin(usuarioLogin);
		usuario = buscarPorLogin(usuario);
		if(usuario.getId() != 0){
			usuario.setSenha(geradorDeSenhaUtil.gerarMD5(novaSenha));
			update(usuario);
		}else{
			throw new UsuarioInexistenteException();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void esqueciMinhaSenha(String usuarioEmail){
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioEmail);
		usuario = buscarPorEmail(usuario);
		if(usuario.getId() != 0){
			String chave = AppUtil.criarChave();
			usuario.setToken(chave);
			update(usuario);
		}else{
			throw new UsuarioInexistenteException();
		}
	}
	
	public boolean verificarToken(String token){
		List<Usuario> usuarios = usuarioPersistence.buscarPorToken(token);
		if(usuarios != null && !usuarios.isEmpty()){
			Usuario usuario = usuarios.get(0);
			String senha = geradorDeSenhaUtil.gerarSenha();
			usuario.setSenha(geradorDeSenhaUtil.gerarMD5(senha));
			usuario.setToken(null);
			update(usuario);
			return true;
		}else{
			throw new ChaveNaoEncontradaException();
		}
	}
	
	public int obterAtivos(){
		return usuarioPersistence.obterAtivos();
	}
	
	public int obterInativos(){
		return usuarioPersistence.obterInativos();
	}

	@Override
	protected UsuarioPersistence getPersistence() {
		return usuarioPersistence;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Usuario domain) {
		usuarioPersistence.update(domain);
	}
	
	public List<Usuario> buscar(Usuario filtros) {
		return usuarioPersistence.buscar(filtros);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(Usuario domain) {
		domain.setAtivo(false);
		usuarioPersistence.update(domain);
	}
}
