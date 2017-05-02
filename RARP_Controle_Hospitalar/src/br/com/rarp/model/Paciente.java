package br.com.rarp.model;

import java.util.List;

public class Paciente extends PessoaFisica {
	private List<Convenio> convenios;
	private List<EntradaPaciente> entradas;
	private List<EvolucaoClinica> evolucoesClinica;
	public List<Convenio> getConvenios() {
		return convenios;
	}
	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}
	public List<EntradaPaciente> getEntradas() {
		return entradas;
	}
	public void setEntradas(List<EntradaPaciente> entradas) {
		this.entradas = entradas;
	}
	public List<EvolucaoClinica> getEvolucoesClinica() {
		return evolucoesClinica;
	}
	public void setEvolucoesClinica(List<EvolucaoClinica> evolucoesClinica) {
		this.evolucoesClinica = evolucoesClinica;
	}
	
}
