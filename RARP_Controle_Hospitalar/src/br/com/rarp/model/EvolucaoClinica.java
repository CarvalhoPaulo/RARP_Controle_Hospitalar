package br.com.rarp.model;

public class EvolucaoClinica extends Movimentacao {
	
	private int codigo;
	private String descricao;
	private EntradaPaciente entradaPaciente;
	private Enfermeira enfermeira;
	private Atendente atendente;
	private Medico medico;
	private Paciente paciente;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public EntradaPaciente getEntradaPaciente() {
		return entradaPaciente;
	}
	public void setEntradaPaciente(EntradaPaciente entradaPaciente) {
		this.entradaPaciente = entradaPaciente;
	}
	public Enfermeira getEnfermeira() {
		return enfermeira;
	}
	public void setEnfermeira(Enfermeira enfermeira) {
		this.enfermeira = enfermeira;
	}
	public Atendente getAtendente() {
		return atendente;
	}
	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
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
	
	
}
