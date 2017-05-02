package br.com.rarp.model;

public class Convenio extends PessoaJuridica {

	private String nome;
	private String ANS;
	private int tipo;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getANS() {
		return ANS;
	}
	public void setANS(String aNS) {
		ANS = aNS;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	

}
