package br.com.rarp.model;

public class Limpeza extends Movimentacao {
	private Funcionario funcionarioLimpeza;
	private Leito espaco;

	public Funcionario getFuncionarioLimpeza() {
		return funcionarioLimpeza;
	}

	public void setFuncionarioLimpeza(Funcionario funcionarioLimpeza) {
		this.funcionarioLimpeza = funcionarioLimpeza;
	}

	public Leito getEspaco() {
		return espaco;
	}

	public void setEspaco(Leito espaco) {
		this.espaco = espaco;
	}
}
