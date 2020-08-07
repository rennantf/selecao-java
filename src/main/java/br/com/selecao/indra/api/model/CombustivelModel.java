package br.com.selecao.indra.api.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.selecao.indra.api.entity.Combustivel;
import br.com.selecao.indra.api.repository.CombustivelRepository;

@Component
public class CombustivelModel {
	private final CombustivelRepository combustivelRepository;

	@Autowired
	public CombustivelModel(CombustivelRepository combustivelRepository) {
		this.combustivelRepository = combustivelRepository;
	}

	public Combustivel salvar(Combustivel combustivel) throws Exception {
		Combustivel combustivelConsulta = getPorNome(combustivel.getNome());

		if (combustivelConsulta != null) {
			throw new Exception("Erro ao cadastrar, já existe um combustível com esse nome!");
		}

		combustivelRepository.save(combustivel);

		return combustivel;
	}

	public void remove(long combustivelId) throws Exception {
		Combustivel combustivel = getById(combustivelId);

		if (combustivel == null) {
			throw new Exception("Erro ao remover, combustível não encontrado!");
		}

		combustivelRepository.deleteById(combustivelId);
	}

	public Combustivel update(Combustivel combustivel) throws Exception {
		Combustivel combustivelConsulta = getPorNome(combustivel.getNome());

		if (combustivelConsulta != null && combustivelConsulta.getId() != combustivel.getId()) {
			throw new Exception("Já existe um combustível com mesmo nome cadastrado");
		}

		return combustivelRepository.save(combustivel);
	}

	public List<Combustivel> getAll() throws Exception {
		return (List<Combustivel>) combustivelRepository.findAll();
	}

	public Combustivel getById(long id) throws Exception {
		return combustivelRepository.findById(id).orElse(null);
	}

	public Combustivel getPorNome(String nome) throws Exception {
		return combustivelRepository.findByNome(nome);
	}
}
