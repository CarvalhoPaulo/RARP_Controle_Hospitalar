package br.com.rarp.model;

import java.util.List;

public class EntradaPaciente extends Movimentacao {

	private String preTriagem;
	private Medico medico;
	private Paciente paciente;
	private Funcionario atendente;
	private Funcionario enfermeira;
	private List<Atendimento> agendamentos;
	private List<Encaminhamento> encaminhamentos;
	
	public String getPreTriagem() {
		return preTriagem;
	}
	public void setPreTriagem(String preTriagem) {
		this.preTriagem = preTriagem;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public List<Encaminhamento> getEncaminhamentos() {
		return encaminhamentos;
	}
	public void setEncaminhamentos(List<Encaminhamento> encaminhamentos) {
		this.encaminhamentos = encaminhamentos;
	}
	public Funcionario getAtendente() {
		return atendente;
	}
	public void setAtendente(Funcionario atendente) {
		this.atendente = atendente;
	}
	public List<Atendimento> getAgendamentos() {
		return agendamentos;
	}
	public void setAgendamentos(List<Atendimento> agendamentos) {
		this.agendamentos = agendamentos;
	}
	public Funcionario getEnfermeira() {
		return enfermeira;
	}
	public void setEnfermeira(Funcionario enfermeira) {
		this.enfermeira = enfermeira;
	}

}
