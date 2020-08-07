package br.com.selecao.indra.api.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.selecao.indra.api.entity.Municipio;
import br.com.selecao.indra.api.repository.MunicipioRepository;

@Component
public class MunicipioModel {
	private final MunicipioRepository municipioRepository;

	@Autowired
	public MunicipioModel(MunicipioRepository municipioRepository) {
		this.municipioRepository = municipioRepository;
	}

	public Municipio salvar(Municipio municipio) throws Exception {

		Municipio municipioConsulta = getPorNome(municipio.getNome());

		if (municipioConsulta != null) {
			throw new Exception("Erro ao cadastrar, já existe um municipio com esse nome!");
		}

		municipioRepository.save(municipio);

		return municipio;
	}

	public void remove(long municipioId) throws Exception {
		Municipio municipio = getById(municipioId);

		if (municipio == null) {
			throw new Exception("Erro ao remover, município não encontrado!");
		}

		municipioRepository.deleteById(municipioId);
	}

	public Municipio update(Municipio municipio) throws Exception {
		Municipio municipioConsulta = getPorNome(municipio.getNome());
		
		if (municipioConsulta != null && municipioConsulta.getId() != municipioConsulta.getId()) {
			throw new Exception("Já existe um município com mesmo nome cadastrado");
		}

		return municipioRepository.save(municipio);
	}

	public List<Municipio> getAll() throws Exception {
		return  (List<Municipio>) municipioRepository.findAll();
	}

	public Municipio getById(long id) throws Exception {
		return municipioRepository.findById(id).orElse(null);
	}
	
	public Municipio getPorNome(String nome) throws Exception {
		return municipioRepository.findByNome(nome);
	}
}
