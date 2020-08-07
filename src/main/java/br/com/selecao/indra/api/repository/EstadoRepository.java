package br.com.selecao.indra.api.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.selecao.indra.api.entity.Estado;

public interface EstadoRepository extends CrudRepository<Estado, Long> {
	
	Estado findBySigla(String sigla);
}
