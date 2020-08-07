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

import br.com.selecao.indra.api.entity.Distribuidora;
import br.com.selecao.indra.api.model.DistribuidoraModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RequestMapping
@RestController
public class DistribuidoraRoute {
	@Autowired
	private DistribuidoraModel distribuidoraModel;

	public DistribuidoraRoute(DistribuidoraModel distribuidoraModel) {
		this.distribuidoraModel = distribuidoraModel;
	}

	@ApiOperation(value = "Salva uma distribuidora")
	@PostMapping(path = "distribuidoras")
	public ResponseEntity<?> save(@RequestBody Distribuidora distribuidora) throws Exception {
		try {
			return new ResponseEntity<>(distribuidoraModel.salvar(distribuidora), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Atualiza uma distribuidora")
	@PutMapping(path = "distribuidoras")
	public ResponseEntity<?> update(@RequestBody Distribuidora distribuidora) throws Exception {
		try {
			return new ResponseEntity<>(distribuidoraModel.update(distribuidora), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Remove uma distribuidora")
	@DeleteMapping(path = "distribuidoras/{id}")
	public ResponseEntity<?> remove(@PathVariable long id) throws Exception {
		try {
			distribuidoraModel.remove(id);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Recupera as distribuidoras")
	@GetMapping(path = "distribuidoras")
	public ResponseEntity<?> getAll() throws Exception {
		try {
			return new ResponseEntity<>(distribuidoraModel.getAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Recupera a distribuidora pelo id")
	@GetMapping(path = "distribuidoras/{id}")
	public ResponseEntity<?> getById(@PathVariable long id) throws Exception {
		try {
			return new ResponseEntity<>(distribuidoraModel.getById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
