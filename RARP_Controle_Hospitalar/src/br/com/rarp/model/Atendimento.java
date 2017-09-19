package br.com.rarp.model;

import java.util.Date;
import java.util.List;

import br.com.rarp.enums.StatusAtendimento;

public class Atendimento extends Movimentacao {

	private Date dataAtendimento;
	private Date horaIni;
	private Date horaFim;
	private String detalheMedico;
	private String descricao;
	private ReceitaMedica receitaMedica;
	private StatusAtendimento statusAtendimento;
	private EntradaPaciente entradaPaciente;
	private Funcionario responsavel;
	private List<Sintoma> sintomas;

	public String getDetalheMedico() {
		return detalheMedico;
	}
	public void setDetalheMedico(String detalheMedico) {
		this.detalheMedico = detalheMedico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ReceitaMedica getReceitaMedica() {
		return receitaMedica;
	}
	public void setReceitaMedica(ReceitaMedica receitaMedica) {
		this.receitaMedica = receitaMedica;
	}
	public Date getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	public Date getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(Date horaIni) {
		this.horaIni = horaIni;
	}
	public StatusAtendimento getStatusAtendimento() {
		return statusAtendimento;
	}
	public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}
	public Date getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}
	public EntradaPaciente getEntradaPaciente() {
		return entradaPaciente;
	}
	public void setEntradaPaciente(EntradaPaciente entradaPaciente) {
		this.entradaPaciente = entradaPaciente;
	}
	public Funcionario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Funcionario responsavel) {
		this.responsavel = responsavel;
	}
	public List<Sintoma> getSintomas() {
		return sintomas;
	}
	public void setSintomas(List<Sintoma> sintomas) {
		this.sintomas = sintomas;
	}

}
