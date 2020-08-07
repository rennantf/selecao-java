package br.com.selecao.indra.api.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.selecao.indra.api.entity.Revenda;
import br.com.selecao.indra.api.model.RevendaModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RequestMapping
@RestController
public class RevendaRoute {
	private RevendaModel revendaModel;
	
	@Autowired
	public RevendaRoute(RevendaModel revendaModel) {
		this.revendaModel = revendaModel;
	}
	
	@ApiOperation(value = "Salva uma revenda")
	@PostMapping(path = "revendas")
	public ResponseEntity<?> save(@RequestBody Revenda revenda) throws Exception {
		try {
			return new ResponseEntity<>(revendaModel.salvar(revenda), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Atualiza uma revenda")
	@PutMapping(path = "revendas")
	public ResponseEntity<?> update(@RequestBody Revenda revenda) throws Exception {
		try {
			return new ResponseEntity<>(revendaModel.update(revenda), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Remove uma revenda")
	@DeleteMapping(path = "revendas/{id}")
	public ResponseEntity<?> remove(@PathVariable long id) throws Exception {
		try {
			revendaModel.remove(id);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Recupera as revendas cadastradas")
	@GetMapping(path = "revendas")
	public ResponseEntity<?> getAll() throws Exception {
		try {
			return new ResponseEntity<>(revendaModel.getAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Recupera a revenda por id")
	@GetMapping(path = "revendas/{id}")
	public ResponseEntity<?> getById(@PathVariable long id) throws Exception {
		try {
			return new ResponseEntity<>(revendaModel.getById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna a média de preço de combustível com base no nome do município")
	@GetMapping(path = "revendas/mediaDePrecoDeVendaPorMunicipio")
	public ResponseEntity<?> getMediaDePrecoDeVendaPorMunicipio() throws Exception {
		try {			
			return new ResponseEntity<>(revendaModel.getMediaDePrecoDeVendaPorMunicipio(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna todas as informações importadas por sigla da região")
	@GetMapping(path = "revendas/informacoesImportadasPorSiglaDaRegiao")
	public ResponseEntity<?> getInformacoesImportadasPorSiglaDaRegiao() throws Exception {
		try {			
			return new ResponseEntity<>(revendaModel.getInformacoesImportadasPorSiglaDaRegiao(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna os dados agrupados por distribuidora")
	@GetMapping(path = "revendas/informacoesAgrupadosPorDistribuidora")
	public ResponseEntity<?> getInformacoesAgrupadosPorDistribuidora() throws Exception {
		try {			
			return new ResponseEntity<>(revendaModel.getInformacoesAgrupadosPorDistribuidora(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna os dados agrupados pela data da coleta")
	@GetMapping(path = "revendas/informacoesAgrupadosPorData")
	public ResponseEntity<?> getInformacoesAgrupadosPorData() throws Exception {
		try {			
			return new ResponseEntity<>(revendaModel.getInformacoesAgrupadosPorData(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna o valor médio do valor da compra e do valor da venda por município")
	@GetMapping(path = "revendas/mediaValorCompraVendaPorMunicipio")
	public ResponseEntity<?> getMediaValorCompraVendaPorMunicipio() throws Exception {
		try {			
			return new ResponseEntity<>(revendaModel.getMediaValorCompraVendaPorMunicipio(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna o valor médio do valor da compra e do valor da venda por bandeira")
	@GetMapping(path = "revendas/mediaValorCompraVendaPorBandeira")
	public ResponseEntity<?> getMediaValorCompraVendaPorBandeira() throws Exception {
		try {			
			return new ResponseEntity<>(revendaModel.getMediaValorCompraVendaPorBandeira(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
