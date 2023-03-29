package br.travelexpense.dto;

import java.sql.Date;

import br.travelexpense.model.Despesa;
import br.travelexpense.model.Viagem;

public class HeaderViagemDto {
	
	private Long id;
	private Date dataInicio;
	private Date dataFinal;
	private String cliente;
	private double gastos;
	private double orcamento;
	private String status;
	
	public HeaderViagemDto(Viagem viagem) {
		this.id = viagem.getId();
		this.dataInicio = viagem.getDtInicio();
		this.dataFinal = viagem.getDtFim();
		this.cliente = viagem.getCliente().getNome();
		this.gastos = viagem.getDespesas().stream().mapToDouble(Despesa::getValor).sum();
		this.orcamento = 0;
		this.status = "EM ANDAMENTO";
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return this.dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public double getGastos() {
		return this.gastos;
	}

	public void setGastos(double gastos) {
		this.gastos = gastos;
	}

	public double getOrcamento() {
		return this.orcamento;
	}

	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
