package br.com.selecao.indra.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.selecao.indra.api.entity.Usuario;
import br.com.selecao.indra.api.model.UsuarioModel;

@Component
public class UsuarioService implements UserDetailsService {
	private UsuarioModel usuarioModel;

	@Autowired
	public UsuarioService(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = null;
		try {
			usuario = usuarioModel.getUsuarioPorUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não localizado.");
		}

		List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(),
				authorityListAdmin);
	}
}
