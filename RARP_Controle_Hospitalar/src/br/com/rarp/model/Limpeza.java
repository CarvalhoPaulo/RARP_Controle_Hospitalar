package br.com.rarp.model;

import java.util.List;

public class Limpeza extends Movimentacao {
	private Funcionario funcionarioLimpeza;
	private List<Leito> leitos;

	public Funcionario getFuncionarioLimpeza() {
		return funcionarioLimpeza;
	}

	public void setFuncionarioLimpeza(Funcionario funcionarioLimpeza) {
		this.funcionarioLimpeza = funcionarioLimpeza;
	}

	public List<Leito> getLeitos() {
		return leitos;
	}

	public void setLeitos(List<Leito> leitos) {
		this.leitos = leitos;
	}
}
