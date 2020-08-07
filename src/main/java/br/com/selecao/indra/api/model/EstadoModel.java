package br.com.selecao.indra.api.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.selecao.indra.api.entity.Estado;
import br.com.selecao.indra.api.repository.EstadoRepository;

@Component
public class EstadoModel {
	private final EstadoRepository estadoRepository;

	@Autowired
	public EstadoModel(EstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}

	public Estado salvar(Estado estado) throws Exception {

		Estado estadoConsulta = getPorSigla(estado.getSigla());

		if (estadoConsulta != null) {
			throw new Exception("Erro ao cadastrar, já existe um estado com essa sigla!");
		}

		estadoRepository.save(estado);

		return estado;
	}

	public void remove(long estadoId) throws Exception {
		Estado estado = getById(estadoId);

		if (estado == null) {
			throw new Exception("Estado não encontrado!");
		}

		estadoRepository.deleteById(estadoId);
	}

	public Estado update(Estado estado) throws Exception {
		Estado estadoConsulta = getPorSigla(estado.getSigla());

		if (estadoConsulta != null && estadoConsulta.getId() != estadoConsulta.getId()) {
			throw new Exception("Já existe um estado com mesmo sigla cadastrada");
		}

		return estadoRepository.save(estado);
	}

	public List<Estado> getAll() throws Exception {
		return  (List<Estado>) estadoRepository.findAll();
	}

	public Estado getById(long id) throws Exception {
		return estadoRepository.findById(id).orElse(null);
	}
	
	public Estado getPorSigla(String sigla) throws Exception {
		return estadoRepository.findBySigla(sigla);
	}
}
