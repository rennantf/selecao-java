package br.com.selecao.indra.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Revenda extends AbstractEntity {

	@ManyToOne
	private Distribuidora distribuidora;

	@ManyToOne
	private Combustivel combustivel;

	@Column(name = "valor_venda", precision = 10, scale = 4)
	private double valorVenda;

	@Column(name = "valor_compra", precision = 10, scale = 4)
	private double valorCompra;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataColeta;

	public Distribuidora getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(Distribuidora distribuidora) {
		this.distribuidora = distribuidora;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Combustivel getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}
}
