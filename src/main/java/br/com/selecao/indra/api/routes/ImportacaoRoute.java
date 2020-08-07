package br.com.selecao.indra.api.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.selecao.indra.api.model.ImportacaoModel;
import io.swagger.annotations.Api;

@Api
@RequestMapping
@RestController
public class ImportacaoRoute {
	private ImportacaoModel importacaoModel;
	
	@Autowired
	public ImportacaoRoute(ImportacaoModel importacaoModel) {
		this.importacaoModel = importacaoModel;
	}

	@PostMapping(path = "importacao/file")
	public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file) throws Exception {
		try {
			importacaoModel.importaArquivoCsv(file);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
