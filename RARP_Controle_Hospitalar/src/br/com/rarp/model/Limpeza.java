package br.com.rarp.model;

public class Limpeza extends Movimentacao {
	private FuncionarioLimpeza funcionarioLimpeza;
	private Espaco espaco;
	public FuncionarioLimpeza getFuncionarioLimpeza() {
		return funcionarioLimpeza;
	}
	public void setFuncionarioLimpeza(FuncionarioLimpeza funcionarioLimpeza) {
		this.funcionarioLimpeza = funcionarioLimpeza;
	}
	public Espaco getEspaco() {
		return espaco;
	}
	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}
	
	
}
