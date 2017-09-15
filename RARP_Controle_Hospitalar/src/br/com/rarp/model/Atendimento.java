package br.com.rarp.model;

public class Atendimento {

	private String detalheConsulta;
	private String detalheMedico;
	private String descricao;
	private ReceitaMedica receitaMedica;
	public String getDetalheConsulta() {
		return detalheConsulta;
	}
	public void setDetalheConsulta(String detalheConsulta) {
		this.detalheConsulta = detalheConsulta;
	}
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
	
	

}
