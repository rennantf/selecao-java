package br.com.selecao.indra.api.model;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.selecao.indra.api.entity.Revenda;
import br.com.selecao.indra.api.repository.RevendaRepository;
import br.com.selecao.indra.api.utils.Util;

@Component
public class RevendaModel {
	private RevendaRepository revendaRepository;

	@Autowired
	public RevendaModel(RevendaRepository revendaRepository) {
		this.revendaRepository = revendaRepository;
	}

	public Revenda salvar(Revenda revenda) throws Exception {

		Revenda revendaConsulta = getById(revenda.getId());

		if (revendaConsulta != null) {
			throw new Exception("Erro ao cadastrar, já existe um revenda com esse id!");
		}

		revendaRepository.save(revenda);

		return revenda;
	}

	public void remove(long revendaId) throws Exception {
		Revenda revenda = getById(revendaId);

		if (revenda == null) {
			throw new Exception("Erro ao remover, revenda não encontrada!");
		}

		revendaRepository.deleteById(revendaId);
	}

	public Revenda update(Revenda revenda) throws Exception {
		Revenda revendaConsulta = getById(revenda.getId());

		if (revendaConsulta == null) {
			throw new Exception("Erro ao atualizar, revenda não encontrada!");
		}

		return revendaRepository.save(revenda);
	}

	public List<Revenda> getAll() throws Exception {
		return  (List<Revenda>) revendaRepository.findAll();
	}

	public Revenda getById(long id) throws Exception {
		return revendaRepository.findById(id).orElse(null);
	}
	
	public List<ObjectNode> getMediaDePrecoDeVendaPorMunicipio() throws Exception {
		List<Tuple> lista = revendaRepository.getMediaDePrecoDeVendaPorMunicipio();
		List<ObjectNode> result = Util.formataJson(lista);
		
		return result;
	}
	
	public List<ObjectNode> getInformacoesImportadasPorSiglaDaRegiao() throws Exception {
		List<Tuple> lista = revendaRepository.getInformacoesImportadasPorSiglaDaRegiao();
		List<ObjectNode> jsonFormatado = Util.formataJson(lista);
		
		return jsonFormatado;
	}
	
	public List<ObjectNode> getInformacoesAgrupadosPorDistribuidora() throws Exception {
		List<Tuple> lista = revendaRepository.getInformacoesAgrupadosPorDistribuidora();
		List<ObjectNode> jsonFormatado = Util.formataJson(lista);
		
		return jsonFormatado;
	}
	
	public List<ObjectNode> getInformacoesAgrupadosPorData() throws Exception {
		List<Tuple> lista = revendaRepository.getInformacoesAgrupadosPorData();
		List<ObjectNode> jsonFormatado = Util.formataJson(lista);
		
		return jsonFormatado;
	}
	
	public List<ObjectNode> getMediaValorCompraVendaPorMunicipio() throws Exception {
		List<Tuple> lista = revendaRepository.getMediaValorCompraVendaPorMunicipio();
		List<ObjectNode> jsonFormatado = Util.formataJson(lista);
		
		return jsonFormatado;
	}
	
	public List<ObjectNode> getMediaValorCompraVendaPorBandeira() throws Exception {
		List<Tuple> lista = revendaRepository.getMediaValorCompraVendaPorBandeira();
		List<ObjectNode> jsonFormatado = Util.formataJson(lista);
		
		return jsonFormatado;
	}
}
