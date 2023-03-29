package br.travelexpense.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.sql.Date;
import java.util.List;

@Entity
@FilterDef(name = "funcionario", parameters = @ParamDef(name = "funcionarioId", type = "long"))
public class Viagem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	Date dtInicio;
	Date dtFim;
	String anotacoes;

	@OneToMany(mappedBy = "viagem", cascade = CascadeType.MERGE)
	@JsonIgnoreProperties("viagem")
	List<Despesa> despesas;

	@ManyToOne()
	@JoinColumn(name = "cliente_id")
	@JsonIgnoreProperties({ "viagens" })
	Cliente cliente;

	@OneToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST})
	@JoinColumn(name = "orcamento_id")
	@JsonIgnoreProperties({ "viagem" })
	Orcamento orcamento;

	@ManyToMany()
	@JoinTable(name = "funcionarios_alocados", joinColumns = {
			@JoinColumn(name = "funcionario_id") }, inverseJoinColumns = { @JoinColumn(name = "viagem_id") })
	@JsonIgnoreProperties({ "viagens" })
	@Filter(name = "funcionario", condition = "id = :funcionarioId")
	List<Funcionario> funcionarios;

	public boolean temFuncionario(long id) {
		return this.getFuncionarios().stream().filter(f -> f.getId().equals(id)).toList().size() > 0 ? true : false;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	public List<Despesa> getDespesas() {
		return this.despesas;
	}

	public void addDespesa(Despesa despesa) {
		this.despesas.add(despesa);
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

}
