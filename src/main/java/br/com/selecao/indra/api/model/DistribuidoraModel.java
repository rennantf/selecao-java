package br.com.selecao.indra.api.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.selecao.indra.api.entity.Distribuidora;
import br.com.selecao.indra.api.repository.DistribuidoraRepository;

@Component
public class DistribuidoraModel {
	private final DistribuidoraRepository distribuidoraRepository;

	@Autowired
	public DistribuidoraModel(DistribuidoraRepository distribuidoraRepository) {
		this.distribuidoraRepository = distribuidoraRepository;
	}

	public Distribuidora salvar(Distribuidora distribuidora) throws Exception {

		Distribuidora distribuidoraConsulta = getPorCNPJ(distribuidora.getCnpj());

		if (distribuidoraConsulta != null) {
			throw new Exception("Erro ao cadastrar, já existe um distribuidora com esse CNPJ!");
		}

		distribuidoraRepository.save(distribuidora);

		return distribuidora;
	}

	public void remove(long distribuidoraId) throws Exception {
		Distribuidora distribuidora = getById(distribuidoraId);

		if (distribuidora == null) {
			throw new Exception("Erro ao remover, distribuidora não encontrada!");
		}

		distribuidoraRepository.deleteById(distribuidoraId);
	}

	public Distribuidora update(Distribuidora distribuidora) throws Exception {
		Distribuidora distribuidoraConsulta = getPorCNPJ(distribuidora.getCnpj());
		
		if (distribuidoraConsulta != null && distribuidoraConsulta.getId() != distribuidora.getId()) {
			throw new Exception("Já existe uma distribuidora com mesmo CNPJ cadastrada");
		}

		return distribuidoraRepository.save(distribuidora);
	}

	public List<Distribuidora> getAll() throws Exception {
		return  (List<Distribuidora>) distribuidoraRepository.findAll();
	}

	public Distribuidora getById(long id) throws Exception {
		return distribuidoraRepository.findById(id).orElse(null);
	}
	
	public Distribuidora getPorCNPJ(String cnpj) throws Exception {
		return distribuidoraRepository.findByCnpj(cnpj);
	}
}
