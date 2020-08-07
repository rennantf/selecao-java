package br.com.selecao.indra.api.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.selecao.indra.api.entity.Regiao;
import br.com.selecao.indra.api.repository.RegiaoRepository;

@Component
public class RegiaoModel {
	private final RegiaoRepository regiaoRepository;

	@Autowired
	public RegiaoModel(RegiaoRepository regiaoRepository) {
		this.regiaoRepository = regiaoRepository;
	}

	public Regiao salvar(Regiao regiao) throws Exception {

		Regiao regiaoConsulta = getPorSigla(regiao.getSigla());

		if (regiaoConsulta != null) {
			throw new Exception("Erro ao cadastrar, já existe uma região com essa sigla!");
		}

		regiaoRepository.save(regiao);

		return regiao;
	}

	public void remove(long regiaoId) throws Exception {
		Regiao regiao = getById(regiaoId);

		if (regiao == null) {
			throw new Exception("Erro ao remover, região não encontrada!");
		}

		regiaoRepository.deleteById(regiaoId);
	}

	public Regiao update(Regiao regiao) throws Exception {
		Regiao regiaoConsulta = getPorSigla(regiao.getSigla());

		if (regiaoConsulta != null && regiaoConsulta.getId() != regiaoConsulta.getId()) {
			throw new Exception("Já existe um estado com mesmo sigla cadastrada");
		}

		return regiaoRepository.save(regiao);
	}

	public List<Regiao> getAll() throws Exception {
		return  (List<Regiao>) regiaoRepository.findAll();
	}

	public Regiao getById(long id) throws Exception {
		return regiaoRepository.findById(id).orElse(null);
	}
	
	public Regiao getPorSigla(String sigla) throws Exception {
		return regiaoRepository.findBySigla(sigla);
	}
}
