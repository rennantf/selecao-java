package br.com.selecao.indra.api.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Combustivel extends AbstractEntity {

	@Column(unique = true)
	private String nome;

	@Column(name = "unidade_medida")
	private String unidadeMedida;

	@OneToMany(mappedBy = "combustivel")
	@JsonIgnore
	private List<Revenda> revendas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public List<Revenda> getRevendas() {
		return revendas;
	}

	public void setRevendas(List<Revenda> revendas) {
		this.revendas = revendas;
	}
}
