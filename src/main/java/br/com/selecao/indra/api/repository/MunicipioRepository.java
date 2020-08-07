package br.com.selecao.indra.api.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.selecao.indra.api.entity.Municipio;

public interface MunicipioRepository extends CrudRepository<Municipio, Long> {
	
	Municipio findByNome(String nome);
}
