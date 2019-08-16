package com.novaeslucas.modelo.persistence;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.novaeslucas.modelo.domain.Usuario;
import com.novaeslucas.modelo.internal.template.PersistenceSupport;

public class UsuarioPersistence extends PersistenceSupport<Usuario> {

	private static final long serialVersionUID = 1L;
	
	private static final String EMPTY_STRING = "";
	
	public List<Usuario> buscarPorLogin(Usuario usuario) {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" from Usuario where lower(login) = :login and ativo = true ");
		params.put("login", usuario.getLogin().toLowerCase());
		hql.append(" order by login ");
		return find(hql.toString(), params);
	}

	public List<Usuario> buscarPorEmail(Usuario usuario) {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" from Usuario where lower(email) = :email ");
		params.put("email", usuario.getEmail().toLowerCase());
		hql.append(" order by email ");
		return find(hql.toString(), params);
	}
	
	public List<Usuario> buscarPorToken(String token) {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" from Usuario where token = :token ");
		params.put("token", token);
		return find(hql.toString(), params);
	}

	public int obterAtivos() {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" from Usuario where ativo = :ativo ");
		params.put("ativo", true);
		return find(hql.toString(), params).size();
	}
	
	public int obterInativos() {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" from Usuario where ativo = :ativo ");
		params.put("ativo", false);
		return find(hql.toString(), params).size();
	}

	public List<Usuario> buscar(Usuario filtros) {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new Hashtable<String, Object>();
		StringBuilder where = new StringBuilder();
		boolean flagWhere = false;
		
		hql.append(" from Usuario ");
		
		if(filtros.getNome() != null && !EMPTY_STRING.equals(filtros.getNome())){
			where.append(" lower(nome) like :nome and ");
			params.put("nome", "%" + filtros.getNome().toLowerCase() + "%");
			flagWhere = true;
		}
		
		if(filtros.isAtivo()){
			where.append(" ativo = true and ");
			flagWhere = true;
		}else{
			where.append(" ativo = false and ");
			flagWhere = true;
		}
		
		if(flagWhere){
			hql.append(" where ");
			hql.append(where.toString().substring(0, where.toString().length() - 4));
		}
		
		hql.append(" order by nome asc ");
		return find(hql.toString(), params);
	}
	
}
