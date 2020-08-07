package br.com.selecao.indra.api.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.selecao.indra.api.entity.Regiao;

public interface RegiaoRepository extends CrudRepository<Regiao, Long> {
	
	Regiao findBySigla(String sigla);
}
