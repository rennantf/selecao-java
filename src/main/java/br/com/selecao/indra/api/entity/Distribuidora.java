package br.com.selecao.indra.api.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Distribuidora extends AbstractEntity {

	@Column(unique = true)
	@CNPJ
	private String cnpj;

	private String bandeira;

	private String nome;

	@OneToMany(mappedBy = "distribuidora")
	@JsonIgnore
	private List<Revenda> revendas;

	@OneToOne
	private Municipio municipio;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public List<Revenda> getRevendas() {
		return revendas;
	}

	public void setRevendas(List<Revenda> revendas) {
		this.revendas = revendas;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
