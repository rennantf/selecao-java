package br.com.selecao.indra.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Usuario extends AbstractEntity {
	public static final String USUARIO_ADMINISTRADOR = "Administrador";

	private String nome;

	private String password;

	@Column(unique = true)
	private String username;

	private boolean isAdmin;

	private String tipo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
