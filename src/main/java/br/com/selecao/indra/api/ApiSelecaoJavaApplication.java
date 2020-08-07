package br.com.selecao.indra.api;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.selecao.indra.api.entity.Usuario;
import br.com.selecao.indra.api.model.UsuarioModel;

@SpringBootApplication
public class ApiSelecaoJavaApplication {
	private UsuarioModel usuarioModel;

	public ApiSelecaoJavaApplication(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiSelecaoJavaApplication.class, args);
	}

	@PostConstruct
	private void init() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome("Admin");
		usuario.setUsername("admin");
		usuario.setPassword("admin");
		usuario.setTipo("Administrador");

		usuarioModel.salvar(usuario);
	}

}
