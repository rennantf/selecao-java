package br.com.selecao.indra.api.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.selecao.indra.api.model.AutorizacaoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping
public class AutorizacaoRoute {
	private AutorizacaoModel autorizacaoModel;
	
	@Autowired
	public AutorizacaoRoute(AutorizacaoModel autorizacaoModel) {
		this.autorizacaoModel = autorizacaoModel;
	}
	
	@ApiOperation(value = "Recupera o usuario através do token")
	@GetMapping(path="autorizacao/info/{token}")
	public ResponseEntity<?> getInfoByToken(@PathVariable String token) throws Exception {		
		return new ResponseEntity<>(autorizacaoModel.getInfoByToken(token), HttpStatus.OK);
	}
}
