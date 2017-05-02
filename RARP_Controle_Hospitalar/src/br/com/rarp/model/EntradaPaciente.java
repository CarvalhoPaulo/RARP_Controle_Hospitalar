package br.com.rarp.model;

import java.util.List;

import br.com.rarp.annotations.Coluna;

public class EntradaPaciente extends Movimentacao {

	private String preTriagem;
	private Medico medico;
	
	@Coluna(posicao=3, descricao="Paciente")
	private Paciente paciente;
	
	@Coluna(posicao=4, descricao="Atendente")
	private Atendente atendente;
	private Enfermeira enfermeira;
	private List<Agendamento> agendamentos;
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
	public Atendente getAtendente() {
		return atendente;
	}
	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}
	public Enfermeira getEnfermeira() {
		return enfermeira;
	}
	public void setEnfermeira(Enfermeira enfermeira) {
		this.enfermeira = enfermeira;
	}
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}
	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
	public List<Encaminhamento> getEncaminhamentos() {
		return encaminhamentos;
	}
	public void setEncaminhamentos(List<Encaminhamento> encaminhamentos) {
		this.encaminhamentos = encaminhamentos;
	}

}
