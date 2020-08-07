package br.com.selecao.indra.api.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.selecao.indra.api.entity.Combustivel;
import br.com.selecao.indra.api.entity.Distribuidora;
import br.com.selecao.indra.api.entity.Estado;
import br.com.selecao.indra.api.entity.Municipio;
import br.com.selecao.indra.api.entity.Regiao;
import br.com.selecao.indra.api.entity.Revenda;

@Component
public class ImportacaoModel {
	private RegiaoModel regiaoModel;
	private EstadoModel estadoModel;
	private MunicipioModel municipioModel;
	private DistribuidoraModel distribuidoraModel;
	private CombustivelModel combustivelModel;
	private RevendaModel revendaModel;

	/**
	 * map utilizados para evitar realizar consultas e duplicidade de informações
	 */
	HashMap<String, Regiao> mapRegioes = new HashMap<String, Regiao>();
	HashMap<String, Estado> mapEstados = new HashMap<String, Estado>();
	HashMap<String, Municipio> mapMunicipios = new HashMap<String, Municipio>();
	HashMap<String, Distribuidora> mapDistribuidoras = new HashMap<String, Distribuidora>();
	HashMap<String, Combustivel> mapCombustiveis = new HashMap<String, Combustivel>();

	@Autowired
	public ImportacaoModel(RegiaoModel regiaoModel, EstadoModel estadoModel, MunicipioModel municipioModel,
			DistribuidoraModel distribuidoraModel, CombustivelModel combustivelModel, RevendaModel revendaModel) {
		this.regiaoModel = regiaoModel;
		this.estadoModel = estadoModel;
		this.municipioModel = municipioModel;
		this.distribuidoraModel = distribuidoraModel;
		this.combustivelModel = combustivelModel;
		this.revendaModel = revendaModel;
	}

	public void importaArquivoCsv(MultipartFile file) throws Exception {
		InputStream inputStream = file.getInputStream();
		Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_16); // ler o arquivo com codificação UTF 16
		BufferedReader arquivo = new BufferedReader(reader);
		arquivo.readLine(); // cabeçalho

		String linha = arquivo.readLine();
		String[] colunas = null;
		try {
			while (linha != null) {
				colunas = linha.split("\t");

				Regiao regiao = importaDadosRegiao(colunas);
				Estado estado = importaDadosEstado(colunas, regiao);
				Municipio municipio = importaDadosMunicipio(colunas, estado);
				Distribuidora distribuidora = importaDadosDistribuidora(colunas, municipio);
				Combustivel combustivel = importaDadosCombustivel(colunas);
				importaDadosRevenda(colunas, distribuidora, combustivel);
				linha = arquivo.readLine();
			}
		} finally {
			reader.close();
		}
	}

	private Regiao importaDadosRegiao(String[] colunas) throws Exception {
		Regiao regiao = null;

		if (mapRegioes.get(colunas[0]) != null) {
			regiao = mapRegioes.get(colunas[0]);
		} else {
			regiao = new Regiao();
			regiao.setSigla(colunas[0]);
			regiao = regiaoModel.salvar(regiao);
			mapRegioes.put(regiao.getSigla(), regiao);
		}

		return regiao;
	}

	private Estado importaDadosEstado(String[] colunas, Regiao regiao) throws Exception {
		Estado estado = null;

		if (mapEstados.get(colunas[1]) != null) { // Evita uma consulta no banco para verificar se o registro existe
			estado = mapEstados.get(colunas[1]);
		} else {
			estado = new Estado();
			estado.setSigla(colunas[1]);
			estado.setRegiao(regiao);
			estado = estadoModel.salvar(estado);
			mapEstados.put(estado.getSigla(), estado);
		}

		return estado;
	}

	private Municipio importaDadosMunicipio(String[] colunas, Estado estado) throws Exception {
		Municipio municipio = null;

		if (mapMunicipios.get(colunas[2]) != null) { // Evita uma consulta no banco para verificar se o registro existe
			municipio = mapMunicipios.get(colunas[2]);
		} else {
			municipio = new Municipio();
			municipio.setNome(colunas[2]);
			municipio.setEstado(estado);
			municipio = municipioModel.salvar(municipio);
			mapMunicipios.put(municipio.getNome(), municipio);
		}

		return municipio;
	}

	private Distribuidora importaDadosDistribuidora(String[] colunas, Municipio municipio) throws Exception {
		Distribuidora distribuidora = null;

		if (mapDistribuidoras.get(colunas[4]) != null) { // Evita uma consulta no banco para verificar se o registro
															// existe
			distribuidora = mapDistribuidoras.get(colunas[4]);
		} else {
			distribuidora = new Distribuidora();
			distribuidora.setMunicipio(municipio);
			distribuidora.setNome(colunas[3]);
			distribuidora.setCnpj(colunas[4]);
			distribuidora.setBandeira(colunas[10]);
			distribuidora = distribuidoraModel.salvar(distribuidora);
			mapDistribuidoras.put(distribuidora.getCnpj(), distribuidora);
		}

		return distribuidora;
	}

	private Combustivel importaDadosCombustivel(String[] colunas) throws Exception {
		Combustivel combustivel = null;

		if (mapCombustiveis.get(colunas[5]) != null) { // Evita uma consulta no banco para verificar se o registro
														// existe
			combustivel = mapCombustiveis.get(colunas[5]);
		} else {
			combustivel = new Combustivel();
			combustivel.setNome(colunas[5]);
			combustivel.setUnidadeMedida(colunas[9]);
			combustivel = combustivelModel.salvar(combustivel);
			mapCombustiveis.put(combustivel.getNome(), combustivel);
		}

		return combustivel;
	}

	private void importaDadosRevenda(String[] colunas, Distribuidora distribuidora, Combustivel combustivel)
			throws Exception {
		double valorVenda = 0.0000;
		double valorCompra = 0.0000;

		if (!"".equals(colunas[7])) {
			valorVenda = Double.parseDouble(colunas[7].replace(",", "."));
		}

		if (!"".equals(colunas[8])) {
			valorCompra = Double.parseDouble(colunas[8].replace(",", "."));
		}

		Revenda revenda = new Revenda();
		revenda.setValorVenda(valorVenda);
		revenda.setValorCompra(valorCompra);
		SimpleDateFormat formatoDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
		revenda.setDataColeta(formatoDDMMYYYY.parse(colunas[6]));
		revenda.setDistribuidora(distribuidora);
		revenda.setCombustivel(combustivel);
		revenda = revendaModel.salvar(revenda);
	}
}
