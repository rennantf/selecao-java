package br.com.selecao.indra.api.model;

import org.springframework.stereotype.Component;

import br.com.selecao.indra.api.entity.Usuario;
import br.com.selecao.indra.api.security.SecurityConstants;
import io.jsonwebtoken.Jwts;

@Component
public class AutorizacaoModel {
	private final UsuarioModel usuarioModel;

	public AutorizacaoModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public Usuario getInfoByToken(String token) throws Exception {
		String username = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();

		return usuarioModel.getUsuarioPorUsername(username);
	}

}
