package br.com.selecao.indra.api.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.selecao.indra.api.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Usuario findByUsername(String username);

}
